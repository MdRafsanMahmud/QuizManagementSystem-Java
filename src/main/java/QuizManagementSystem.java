// Importing Java standard libraries
import java.io.*; // For handling input/output like FileReader and FileWriter
import java.util.*; // For Scanner, List, ArrayList, Collections.shuffle

// Importing Gson libraries to work with JSON
import com.google.gson.Gson; // Core class to convert Java objects to/from JSON
import com.google.gson.GsonBuilder; // To build Gson object with formatting options
import com.google.gson.reflect.TypeToken; // To preserve type information when reading lists from JSON

// User class maps each object from users.json to a Java object
class User {
    String username; // stores username of the user
    String password; // stores password of the user
    String role;     // "admin" or "student"
}

// Question class represents each quiz question from quiz.json
class Question {
    String question;  // actual question text
    String option1;   // option 1
    String option2;   // option 2
    String option3;   // option 3
    String option4;   // option 4
    int answerkey;    // correct answer index (1 to 4)
}

// Main class of the program
public class QuizManagementSystem {

    // File paths for JSON files
    static final String USERS_FILE = "users.json"; // path to user credentials
    static final String QUIZ_FILE = "quiz.json";   // path to quiz questions

    static Scanner sc = new Scanner(System.in); // For reading user input from console

    // Gson object for handling JSON, with pretty printing (for readable formatting)
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        // Prompt the user to enter username and password
        System.out.print("Enter your username: ");
        String username = sc.nextLine(); // reads input line for username
        System.out.print("Enter password: ");
        String password = sc.nextLine(); // reads input line for password

        // Load all users from JSON file into a List<User>
        List<User> users = loadUsers();

        // Loop through users to verify credentials
        for (User user : users) {
            // Check if username and password match
            if (user.username.equals(username) && user.password.equals(password)) {
                // Check if the user is an admin
                if (user.role.equals("admin")) {
                    System.out.println("Welcome admin! Please create new questions in the question bank.");
                    adminMenu(); // Call admin menu method to add questions
                }
                // If user is student
                else if (user.role.equals("student")) {
                    System.out.println("Welcome " + username + " to the quiz! We will throw you 10 questions.");
                    System.out.println("Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");
                    // If student presses 's', start quiz
                    if (sc.nextLine().equalsIgnoreCase("s")) {
                        studentQuiz();
                    }
                }
                return; // Stop checking further once user is authenticated
            }
        }
        // If no matching credentials found
        System.out.println("Invalid credentials!");
    }

    // Admin feature: Allows continuously adding questions
    static void adminMenu() {
        while (true) {
            System.out.print("Input your question (or 'q' to quit): ");
            String q = sc.nextLine();
            if (q.equalsIgnoreCase("q")) break; // break loop if input is 'q'

            // Ask for 4 options from the admin
            System.out.print("Input option 1: ");
            String o1 = sc.nextLine();
            System.out.print("Input option 2: ");
            String o2 = sc.nextLine();
            System.out.print("Input option 3: ");
            String o3 = sc.nextLine();
            System.out.print("Input option 4: ");
            String o4 = sc.nextLine();
            System.out.print("What is the answer key (1-4)? ");
            int key = Integer.parseInt(sc.nextLine()); // converts input string to integer

            // Create and fill Question object
            Question question = new Question();
            question.question = q;
            question.option1 = o1;
            question.option2 = o2;
            question.option3 = o3;
            question.option4 = o4;
            question.answerkey = key;

            // Save this question to quiz.json
            saveQuestion(question);
            System.out.println("Saved successfully!");
        }
    }

    // Method for student to take quiz
    static void studentQuiz() {
        List<Question> questions = loadQuestions(); // load questions from quiz.json

        // If there are fewer than 10 questions, quiz cannot be taken
        if (questions.size() < 10) {
            System.out.println("Not enough questions in the question bank.");
            return;
        }

        Collections.shuffle(questions); // randomly shuffle questions
        int score = 0; // to keep student score

        // Ask 10 questions one by one
        for (int i = 0; i < 10; i++) {
            Question q = questions.get(i); // get question i
            System.out.println("[Question " + (i+1) + "] " + q.question);
            System.out.println("1. " + q.option1);
            System.out.println("2. " + q.option2);
            System.out.println("3. " + q.option3);
            System.out.println("4. " + q.option4);
            System.out.print("Your answer: ");
            int ans = Integer.parseInt(sc.nextLine()); // convert answer to integer
            if (ans == q.answerkey) score++; // increase score if correct
        }

        // Show result based on score
        System.out.println("\nYour Score: " + score + "/10");
        if (score >= 8) System.out.println("Excellent! You have got " + score + " out of 10");
        else if (score >= 5) System.out.println("Good. You have got " + score + " out of 10");
        else if (score >= 3) System.out.println("Very poor! You have got " + score + " out of 10");
        else System.out.println("Very sorry you are failed. You have got " + score + " out of 10");

        // Ask if student wants to retry
        System.out.println("Would you like to start again? Press 's' for start or 'q' for quit");
        if (sc.nextLine().equalsIgnoreCase("s")) studentQuiz();
    }

    // Load user list from users.json file
    static List<User> loadUsers() {
        try (Reader reader = new FileReader(USERS_FILE)) {
            // Use Gson to convert JSON array into List<User>
            return gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
        } catch (IOException e) {
            // If file doesn't exist or has error, return empty list
            return new ArrayList<>();
        }
    }

    // Load question list from quiz.json file
    static List<Question> loadQuestions() {
        try (Reader reader = new FileReader(QUIZ_FILE)) {
            return gson.fromJson(reader, new TypeToken<List<Question>>(){}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // Save a new question to quiz.json file
    static void saveQuestion(Question q) {
        List<Question> questions = loadQuestions(); // Load existing questions
        questions.add(q); // Add new question to list

        try (Writer writer = new FileWriter(QUIZ_FILE)) {
            // Overwrite quiz.json with updated question list
            gson.toJson(questions, writer);
        } catch (IOException e) {
            System.out.println("Error saving question.");
        }
    }
}
