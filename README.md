# FuzzyRank – Fuzzy Logic–Based Candidate Ranking System

## 1. Project Title & Goal

FuzzyRank is a Spring Boot–based decision support system that evaluates and ranks candidates using fuzzy logic and multi-criteria analysis to enable fair decision-making under uncertainty.

---

## 2. Setup Instructions

### Prerequisites
- Java 8 or higher
- Maven
- IntelliJ IDEA or any Java IDE

### Steps to Run the Project

mvn clean install  
mvn spring-boot:run  

After the application starts, open Swagger UI:

http://localhost:8080/swagger-ui/index.html

---

## 3. The Logic (How I Thought)

### Why did I choose this approach?

Traditional decision systems rely on strict thresholds (for example, fixed CGPA cutoffs), which do not reflect real-world human judgment. Real decisions often involve uncertainty and subjective evaluation.

I chose a fuzzy logic–based approach because it allows partial membership (Low, Medium, High), supports multi-criteria evaluation, and mimics human reasoning instead of binary decisions.

The system uses a Mamdani fuzzy inference model, implemented completely from scratch in Java, to deeply understand fuzzy decision-making rather than relying on external libraries.

---

### What was the hardest bug you faced, and how did you fix it?

The most challenging issue occurred when the system returned a final score of 0.0 for candidates with 0 years of experience, even when other attributes were strong.

Root Cause:  
The membership functions initially excluded boundary values (such as experience = 0). This resulted in zero membership across all fuzzy sets, causing no rule to fire and producing a zero score.

Solution:  
- Redesigned membership functions to be boundary-inclusive  
- Used trapezoidal membership functions for edge cases  
- Added a default fallback rule to ensure at least one rule always fires  

This fix ensured correct fuzzy inference and realistic scoring.

---

## 4. Output Screenshots

Swagger UI – API Loaded Successfully  
(images/swagger-ui.png)

Evaluate Single Candidate API Output  
(images/evaluate-output.png)

Rank Multiple Candidates API Output  
(images/ranking-output.png)

All screenshots are stored inside the images/ directory.

---

## 5. Future Improvements

If I had two more days, I would add:

1. Database Integration (MySQL + JPA)  
   To persist candidate data and ranking history.

2. Unit Testing (JUnit)  
   To validate membership functions, rule evaluation, and edge cases automatically.

These improvements would make the system more scalable and production-ready.
