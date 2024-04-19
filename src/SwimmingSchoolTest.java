import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class SwimmingSchoolTest {

    @Test
    public void testLessonConstructor() {
        Coach coach = new Coach("John");
        Lesson lesson = new Lesson("Monday", "4-5pm", 1, coach);
        assertEquals("Monday", lesson.getDay());
        assertEquals("4-5pm", lesson.getTimeSlot());
        assertEquals(1, lesson.getGradeLevel());
        assertEquals(coach, lesson.getCoach());
        assertEquals("booked", lesson.getStatus());
    }

    @Test
    public void testCoachConstructor() {
        Coach coach = new Coach("John");
        assertEquals("John", coach.getName());
    }

    @Test
    public void testLearnerConstructor() {
        Learner learner = new Learner(1, "Alice", "Female", 10, "123456789", 1);
        assertEquals(1, learner.getUserId());
        assertEquals("Alice", learner.getName());
        assertEquals("Female", learner.getGender());
        assertEquals(10, learner.getAge());
        assertEquals("123456789", learner.getEmergencyContact());
        assertEquals(1, learner.getGradeLevel());
        assertTrue(learner.getBookedLessons().isEmpty());
    }

    @Test
    public void testBookLesson() {
        SwimmingSchool swimmingSchool = new SwimmingSchool();
        Lesson lesson = new Lesson("Monday", "4-5pm", 1, new Coach("John"));
        Learner learner = new Learner(1, "Alice", "Female", 10, "123456789", 1);
        swimmingSchool.bookLesson(learner, lesson);
        assertTrue(learner.getBookedLessons().contains(lesson));
        assertEquals("booked", lesson.getStatus());
    }



    @Test
    public void testGetLessonsByDay() {
        SwimmingSchool swimmingSchool = new SwimmingSchool();
        List<Lesson> lessons = swimmingSchool.getLessonsByDay("Monday");
        assertEquals(2, lessons.size());
        assertEquals("Monday", lessons.get(0).getDay());
    }

    @Test
    public void testGetLessonsByGradeLevel() {
        SwimmingSchool swimmingSchool = new SwimmingSchool();
        List<Lesson> lessons = swimmingSchool.getLessonsByGradeLevel(1);
        assertEquals(2, lessons.size());
        assertEquals(1, lessons.get(0).getGradeLevel());
    }

    @Test
    public void testGetLessonsByCoach() {
        SwimmingSchool swimmingSchool = new SwimmingSchool();
        List<Lesson> lessons = swimmingSchool.getLessonsByCoach("Helen");
        assertEquals(4, lessons.size());
        assertEquals("Helen", lessons.get(0).getCoach().getName());
    }

    @Test
    public void testChangeBooking() {
        SwimmingSchool swimmingSchool = new SwimmingSchool();
        Lesson lesson1 = new Lesson("Monday", "4-5pm", 1, new Coach("John"));
        Lesson lesson2 = new Lesson("Wednesday", "4-5pm", 1, new Coach("John"));
        Learner learner = new Learner(1, "Alice", "Female", 10, "123456789", 1);
        learner.bookLesson(lesson1);
        swimmingSchool.changeBooking(learner, lesson1, lesson2);
        assertFalse(learner.getBookedLessons().contains(lesson1));
        assertTrue(learner.getBookedLessons().contains(lesson2));
        assertEquals("cancelled", lesson1.getStatus());
        assertEquals("booked", lesson2.getStatus());
    }

    @Test
    public void testRecordReview() {
        SwimmingSchool swimmingSchool = new SwimmingSchool();
        Lesson lesson = new Lesson("Monday", "4-5pm", 1, new Coach("John"));
        Learner learner = new Learner(1, "Alice", "Female", 10, "123456789", 1);
        learner.bookLesson(lesson);
        swimmingSchool.recordReview(learner, lesson, "Great lesson!", 5);
    }

    @Test
    public void testAddNewLearner() {
        SwimmingSchool swimmingSchool = new SwimmingSchool();
        Learner learner = new Learner(1, "Alice", "Female", 10, "123456789", 1);
        swimmingSchool.addNewLearner(learner);
        assertTrue(swimmingSchool.getLearners().contains(learner));
    }

}
