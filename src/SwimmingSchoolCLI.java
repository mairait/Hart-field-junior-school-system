    import java.util.*;

    class Lesson {
        private String day;
        private String timeSlot;
        private int gradeLevel;
        private Coach coach;
        private String status;

        public Lesson(String day, String timeSlot, int gradeLevel, Coach coach) {
            this.day = day;
            this.timeSlot = timeSlot;
            this.gradeLevel = gradeLevel;
            this.coach = coach;
            this.status = "booked";
        }

        public String getDay() {
            return day;
        }

        public String getTimeSlot() {
            return timeSlot;
        }

        public int getGradeLevel() {
            return gradeLevel;
        }

        public Coach getCoach() {
            return coach;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isFull() {
            return false;
        }
    }

    class Coach {
        private String name;

        public Coach(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class Learner {
        private int userId;
        private String name;
        private String gender;
        private int age;
        private String emergencyContact;
        private int gradeLevel;
        private List<Lesson> bookedLessons;

        public Learner(int userId, String name, String gender, int age, String emergencyContact, int gradeLevel) {
            this.userId = userId;
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.emergencyContact = emergencyContact;
            this.gradeLevel = gradeLevel;
            this.bookedLessons = new ArrayList<>();
        }

        // Getters and setters
        public int getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public int getAge() {
            return age;
        }

        public String getEmergencyContact() {
            return emergencyContact;
        }

        public int getGradeLevel() {
            return gradeLevel;
        }

        public List<Lesson> getBookedLessons() {
            return bookedLessons;
        }

        public void bookLesson(Lesson lesson) {
            bookedLessons.add(lesson);
        }

        public void cancelLesson(Lesson lesson) {
            bookedLessons.remove(lesson);
        }
    }

    class SwimmingSchool {
        private List<Lesson> lessons;
        private List<Coach> coaches;
        private List<Learner> learners;

        public SwimmingSchool() {
            this.lessons = new ArrayList<>();
            this.coaches = new ArrayList<>();
            this.learners = new ArrayList<>();
            initializeLessons();
            initializeCoaches();
        }

        private void initializeLessons() {

            Coach coach1 = new Coach("Helen");
            Coach coach2 = new Coach("John");
            Coach coach3 = new Coach("Emily");

            lessons.add(new Lesson("Monday", "4-5pm", 1, coach1));
            lessons.add(new Lesson("Monday", "5-6pm", 2, coach1));
            lessons.add(new Lesson("Wednesday", "4-5pm", 3, coach2));
            lessons.add(new Lesson("Wednesday", "5-6pm", 4, coach2));
            lessons.add(new Lesson("Friday", "4-5pm", 5, coach3));
            lessons.add(new Lesson("Friday", "5-6pm", 1, coach3));
            lessons.add(new Lesson("Saturday", "2-3pm", 2, coach1));
            lessons.add(new Lesson("Saturday", "3-4pm", 3, coach1));
        }

        private void initializeCoaches() {
            coaches.add(new Coach("Helen"));
            coaches.add(new Coach("John"));
            coaches.add(new Coach("Emily"));
        }

        public List<Lesson> getLessonsByDay(String day) {
            List<Lesson> filteredLessons = new ArrayList<>();
            for (Lesson lesson : lessons) {
                if (lesson.getDay().equalsIgnoreCase(day)) {
                    filteredLessons.add(lesson);
                }
            }
            return filteredLessons;
        }

        public List<Lesson> getLessonsByGradeLevel(int gradeLevel) {
            List<Lesson> filteredLessons = new ArrayList<>();
            for (Lesson lesson : lessons) {
                if (lesson.getGradeLevel() == gradeLevel) {
                    filteredLessons.add(lesson);
                }
            }
            return filteredLessons;
        }

        public List<Lesson> getLessonsByCoach(String coachName) {
            List<Lesson> filteredLessons = new ArrayList<>();
            for (Lesson lesson : lessons) {
                if (lesson.getCoach().getName().equalsIgnoreCase(coachName)) {
                    filteredLessons.add(lesson);
                }
            }
            return filteredLessons;
        }

        public void bookLesson(Learner learner, Lesson lesson) {
            learner.bookLesson(lesson);
            lesson.setStatus("booked");
        }

        public void changeBooking(Learner learner, Lesson currentLesson, Lesson newLesson) {
            learner.cancelLesson(currentLesson);
            learner.bookLesson(newLesson);
            currentLesson.setStatus("cancelled");
            newLesson.setStatus("booked");
        }

        public List<Lesson> getBookedLessonsByMonth(Learner learner, int monthNumber) {
            return new ArrayList<>();
        }

        public List<Lesson> getCancelledLessonsByMonth(Learner learner, int monthNumber) {
            return new ArrayList<>();
        }

        public List<Lesson> getAttendedLessonsByMonth(Learner learner, int monthNumber) {
            return new ArrayList<>();
        }

        public List<Integer> getCoachRatingsByMonth(Coach coach, int monthNumber) {
            return new ArrayList<>();
        }

        public void recordReview(Learner learner, Lesson lesson, String review, int rating) {
        }

        public List<Learner> getLearners() {
            return learners;
        }

        public List<Coach> getCoaches() {
            return coaches;
        }

        public void addNewLearner(Learner learner) {
            learners.add(learner);
        }
    }

    public class SwimmingSchoolCLI {
        private static Scanner scanner = new Scanner(System.in);
        private static SwimmingSchool swimmingSchool = new SwimmingSchool();
        private static int nextUserId = 1;

        public static void main(String[] args) {
            System.out.println("Welcome to the Hatfield Junior Swimming School!");

            boolean exit = false;
            while (!exit) {
                printMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        bookLesson();
                        break;
                    case 2:
                        changeOrCancelBooking();
                        break;
                    case 3:
                        attendLesson();
                        break;
                    case 4:
                        printMonthlyLearnerReport();
                        break;
                    case 5:
                        printMonthlyCoachReport();
                        break;
                    case 6:
                        registerNewLearner();
                        break;
                    case 7:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            }

            System.out.println("Thank you for using the Swimming School system. Goodbye!");

        }

        private static void printMenu() {
            System.out.println("\nSelect an option:");
            System.out.println("1. Book a swimming lesson");
            System.out.println("2. Change/Cancel a booking");
            System.out.println("3. Attend a swimming lesson");
            System.out.println("4. Monthly learner report");
            System.out.println("5. Monthly coach report");
            System.out.println("6. Register a new learner");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
        }

        private static void bookLesson() {

            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("\nHow would you like to view the timetable?");
            System.out.println("1. By specifying the day");
            System.out.println("2. By specifying the grade level");
            System.out.println("3. By specifying the coach's name");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            List<Lesson> filteredLessons = new ArrayList<>();
            switch (choice) {
                case 1:
                    System.out.print("Enter the day (e.g., Monday, Wednesday): ");
                    String day = scanner.nextLine();
                    filteredLessons = swimmingSchool.getLessonsByDay(day);
                    break;
                case 2:
                    System.out.print("Enter the grade level (1-5): ");
                    int gradeLevel = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    filteredLessons = swimmingSchool.getLessonsByGradeLevel(gradeLevel);
                    break;
                case 3:
                    System.out.print("Enter the coach's name: ");
                    String coachName = scanner.nextLine();
                    filteredLessons = swimmingSchool.getLessonsByCoach(coachName);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            System.out.println("Available Lessons:");
            printLessons(filteredLessons);
            System.out.print("Enter the lesson number to book: ");
            int lessonNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (lessonNumber < 1 || lessonNumber > filteredLessons.size()) {
                System.out.println("Invalid lesson number.");
                return;
            }

            Lesson selectedLesson = filteredLessons.get(lessonNumber - 1);
            Learner learner = getLoggedInLearner(); // You need to implement a method to get the logged-in learner
            if (learner == null) {
                System.out.println("You need to log in as a learner to book a lesson.");
                return;
            }

            if (selectedLesson.isFull()) {
                System.out.println("The lesson is already full. Booking failed.");
                return;
            }

            if (learner.getBookedLessons().contains(selectedLesson)) {
                System.out.println("You have already booked this lesson. Booking failed.");
                return;
            }

            int learnerGrade = learner.getGradeLevel();
            int lessonGrade = selectedLesson.getGradeLevel();
            if (learnerGrade != lessonGrade && learnerGrade + 1 != lessonGrade) {
                System.out.println("You can only book a lesson of the same grade level or the next grade level. Booking failed.");
                return;
            }

            swimmingSchool.bookLesson(learner, selectedLesson);
            System.out.println(name+"'s Lesson booked successfully.");
        }

        private static void changeOrCancelBooking() {
            Learner learner = getLoggedInLearner();
            if (learner == null) {

            }

            System.out.println("\nBooked Lessons:");
            printBookedLessons(learner);
            System.out.print("Enter the booking number to change/cancel: ");
            int bookingNumber = scanner.nextInt();
            scanner.nextLine();

            if (bookingNumber < 1 || bookingNumber > learner.getBookedLessons().size()) {
                System.out.println("Invalid booking number.");
                return;
            }

            Lesson currentLesson = learner.getBookedLessons().get(bookingNumber - 1);

            System.out.println("\nHow would you like to view the timetable?");
            System.out.println("1. By specifying the day");
            System.out.println("2. By specifying the grade level");
            System.out.println("3. By specifying the coach's name");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            List<Lesson> filteredLessons = new ArrayList<>();
            switch (choice) {
                case 1:
                    System.out.print("Enter the day (e.g., Monday, Wednesday): ");
                    String day = scanner.nextLine();
                    filteredLessons = swimmingSchool.getLessonsByDay(day);
                    break;
                case 2:
                    System.out.print("Enter the grade level (1-5): ");
                    int gradeLevel = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    filteredLessons = swimmingSchool.getLessonsByGradeLevel(gradeLevel);
                    break;
                case 3:
                    System.out.print("Enter the coach's name: ");
                    String coachName = scanner.nextLine();
                    filteredLessons = swimmingSchool.getLessonsByCoach(coachName);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            System.out.println("Available Lessons:");
            printLessons(filteredLessons);
            System.out.print("Enter the new lesson number: ");
            int newLessonNumber = scanner.nextInt();
            scanner.nextLine();

            if (newLessonNumber < 1 || newLessonNumber > filteredLessons.size()) {
                System.out.println("Invalid lesson number.");
                return;
            }

            Lesson newLesson = filteredLessons.get(newLessonNumber - 1);
            if (newLesson.isFull()) {
                System.out.println("The lesson is already full. Change failed.");
                return;
            }

            swimmingSchool.changeBooking(learner, currentLesson, newLesson);
            System.out.println("Booking changed successfully.");
        }

        private static void attendLesson() {
            Learner learner = getLoggedInLearner();
            if (learner == null) {

            }

            System.out.println("\nBooked Lessons:");
            printBookedLessons(learner);
            System.out.print("Enter the booking number to attend: ");
            int bookingNumber = scanner.nextInt();
            scanner.nextLine();

            if (bookingNumber < 1 || bookingNumber > learner.getBookedLessons().size()) {
                System.out.println("Invalid booking number.");
                return;
            }

            Lesson lesson = learner.getBookedLessons().get(bookingNumber - 1);
            lesson.setStatus("attended");

            System.out.print("Write a review for the lesson: ");
            String review = scanner.nextLine();
            System.out.print("Provide a numerical rating for the coach (1-5): ");
            int rating = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            swimmingSchool.recordReview(learner, lesson, review, rating);
            System.out.println("Review recorded successfully.");
        }

        private static void printLessonsWithStatus(List<Lesson> lessons) {
            for (int i = 0; i < lessons.size(); i++) {
                Lesson lesson = lessons.get(i);
                System.out.println((i + 1) + ". " + lesson.getDay() + " " + lesson.getTimeSlot() +
                        " Grade " + lesson.getGradeLevel() + " with Coach " + lesson.getCoach().getName() +
                        " (Status: " + lesson.getStatus() + ")");
            }
        }

        private static void printMonthlyLearnerReport() {
            Learner learner = getLoggedInLearner();
            if (learner == null) {
                System.out.println("You need to log in as a learner to view the monthly report.");
                return;
            }

            System.out.print("Enter the month number (e.g., 03 for March): ");
            int monthNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            List<Lesson> bookedLessons = swimmingSchool.getBookedLessonsByMonth(learner, monthNumber);
            List<Lesson> cancelledLessons = swimmingSchool.getCancelledLessonsByMonth(learner, monthNumber);
            List<Lesson> attendedLessons = swimmingSchool.getAttendedLessonsByMonth(learner, monthNumber);

            System.out.println("\nMonthly Learner Report:");
            System.out.println("Booked Lessons:");
            printLessonsWithStatus(bookedLessons);
            System.out.println("Cancelled Lessons:");
            printLessonsWithStatus(cancelledLessons);
            System.out.println("Attended Lessons:");
            printLessonsWithStatus(attendedLessons);

            System.out.println("Total booked lessons: " + bookedLessons.size());
            System.out.println("Total cancelled lessons: " + cancelledLessons.size());
            System.out.println("Total attended lessons: " + attendedLessons.size());
        }

        private static void printMonthlyCoachReport() {
            Coach coach = getLoggedInCoach();


            System.out.print("Enter the month number (e.g., 03 for March): ");
            int monthNumber = scanner.nextInt();
            scanner.nextLine();

            List<Integer> ratings = swimmingSchool.getCoachRatingsByMonth(coach, monthNumber);
            if (ratings.isEmpty()) {
                System.out.println("No ratings available for this coach in the specified month.");
                return;
            }

            int totalRatings = 0;
            for (int rating : ratings) {
                totalRatings += rating;
            }

            double averageRating = (double) totalRatings / ratings.size();
            System.out.println("Monthly Coach Report:");
            System.out.println("Coach: " + coach.getName());
            System.out.println("Average rating: " + averageRating);
        }

        private static void registerNewLearner() {
            System.out.print("Enter learner's name: ");
            String name = scanner.nextLine();
            System.out.print("Enter learner's gender: ");
            String gender = scanner.nextLine();
            System.out.print("Enter learner's age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter learner's emergency contact: ");
            String emergencyContact = scanner.nextLine();
            System.out.print("Enter learner's grade level: ");
            int gradeLevel = scanner.nextInt();
            scanner.nextLine();

            Learner newLearner = new Learner(nextUserId++, name, gender, age, emergencyContact, gradeLevel);
            swimmingSchool.addNewLearner(newLearner);
            System.out.println("New learner registered successfully.");
        }

        private static void printLessons(List<Lesson> lessons) {
            for (int i = 0; i < lessons.size(); i++) {
                Lesson lesson = lessons.get(i);
                System.out.println((i + 1) + ". " + lesson.getDay() + " " + lesson.getTimeSlot() +
                        " Grade " + lesson.getGradeLevel() + " with Coach " + lesson.getCoach().getName());
            }
        }

        private static void printBookedLessons(Learner learner) {
            List<Lesson> bookedLessons = learner.getBookedLessons();
            for (int i = 0; i < bookedLessons.size(); i++) {
                Lesson lesson = bookedLessons.get(i);
                System.out.println((i + 1) + ". " + lesson.getDay() + " " + lesson.getTimeSlot() +
                        " Grade " + lesson.getGradeLevel() + " with Coach " + lesson.getCoach().getName() +
                        " (Status: " + lesson.getStatus() + ")");
            }
        }

        private static Learner getLoggedInLearner() {


            return new Learner(1, "John Doe", "Male", 10, "123456789", 1);
        }

        private static Coach getLoggedInCoach() {
            return null;
        }
    }
