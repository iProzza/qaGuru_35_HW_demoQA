package qaguru.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomTestDataGenerator {

    private static final Faker faker = new Faker(new Locale("en-GB"));

    public static String getRandomFirstName(){
        return faker.name().firstName();
    };

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getRandomMobile() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String getRandomBirthDay() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public static String getRandomBirthMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public static String getRandomBirthYear() {
        return String.valueOf(faker.number().numberBetween(1950, 2000));
    }

    public static String getRandomSubject() {
        return faker.options().option(
                "Chemistry", "Maths", "Physics", "Arts", "English",
                "Biology", "History", "Economics", "Computer Science");
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }
}
