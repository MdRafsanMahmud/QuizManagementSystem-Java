# 📚 Quiz Management System - Java

A **role-based multiple-choice quiz system** built with Java, using JSON files for data storage. This console-based application allows:
- 👨‍🏫 **Admins** to add quiz questions
- 👨‍🎓 **Students** to take quizzes and get scored based on performance

---

## 🚀 Features

- 🔐 **Role-based Login System** (Admin / Student)
- 📂 **Data Persistence** using `users.json` and `quiz.json`
- 🧠 **Student Quiz Module** with 10 random MCQs per session
- 📝 **Admin Question Bank Management**
- 🎯 **Score Evaluation with Feedback**
- 💾 JSON parsing using Google's `Gson` library

---

## 📁 File Structure

```
QuizManagementSystem-Java/
├── src/
│   └── main/
│       └── java/
│           └── QuizManagementSystem.java
├── users.json           # Stores user credentials and roles
├── quiz.json            # Stores the MCQ question bank
├── build.gradle         # Gradle build file with Gson dependency
├── .gitignore
└── README.md
```

---

## 📄 JSON File Format

### ✅ `users.json`

```json
[
  { "username": "admin", "password": "1234", "role": "admin" },
  { "username": "salman", "password": "1234", "role": "student" }
]
```

### ✅ `quiz.json`

```json
[
  {
    "question": "Which is not part of system testing?",
    "option1": "Regression Testing",
    "option2": "Sanity Testing",
    "option3": "Load Testing",
    "option4": "Unit Testing",
    "answerkey": 4
  }
]
```

---

## 🧪 How to Run the Project

1. ✅ **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/QuizManagementSystem-Java.git
   ```

2. ✅ **Add your questions and users in** `quiz.json` and `users.json`

3. ✅ **Build and run using Gradle**
   ```bash
   ./gradlew build       # For Linux/Mac
   .\gradlew build       # For Windows

   java -cp build/classes/java/main QuizManagementSystem
   ```

---

## 📽️ Demo Video
### 🔐 Admin Mode


https://github.com/user-attachments/assets/9076dfac-c75b-4794-8e68-31cbc5fc53dd



### 🎓 Student Mode


https://github.com/user-attachments/assets/cecf306f-1bd9-4559-9f7d-2569a57865c1




---

## ⚙️ Tech Stack

- **Language:** Java
- **Data Storage:** JSON (users & questions)
- **Dependency:** [Gson](https://github.com/google/gson)
- **Build Tool:** Gradle

---

## 📌 Submission Checklist

- ✅ Complete project uploaded to GitHub  
- ✅ `.idea`, `.gradle`, `build`, `gradle` folders added to `.gitignore`  
- ✅ Clear and structured `README.md`  
- ✅ Video demo showcasing Admin & Student flows

---

## 📣 Contribution & Feedback

This is a solo academic project created to learn Java and showcase software testing logic. Suggestions and improvements are welcome!
