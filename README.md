# 🎓 College Management System — Spring Data JPA

A full-featured college management REST API built with **Spring Boot** and **Spring Data JPA**, demonstrating real-world backend engineering patterns including entity relationships, dynamic queries, pagination, sorting, cascading, and fetch strategies.

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Persistence | Spring Data JPA + Hibernate |
| Database | MySQL / PostgreSQL |
| Build Tool | Maven |
| API Testing | Postman / Swagger UI |

---

## 📐 Entity Schema

### Professor
| Field | Type | Notes |
|-------|------|-------|
| id | Long | PK, Auto-generated |
| title | String | e.g. Dr., Prof. |
| firstName | String | |
| lastName | String | |
| email | String | Unique |
| department | String | |
| salary | Double | |
| hireDate | LocalDate | |
| subjects | List\<Subject\> | OneToMany |
| students | List\<Student\> | ManyToMany |

### Student
| Field | Type | Notes |
|-------|------|-------|
| id | Long | PK, Auto-generated |
| name | String | |
| email | String | Unique |
| enrollmentDate | LocalDate | |
| gpa | Double | |
| status | Enum | ACTIVE, SUSPENDED, GRADUATED |
| professors | List\<Professor\> | ManyToMany |
| subjects | List\<Subject\> | ManyToMany |
| admissionRecord | AdmissionRecord | OneToOne, Cascade |

### Subject
| Field | Type | Notes |
|-------|------|-------|
| id | Long | PK, Auto-generated |
| title | String | |
| courseCode | String | Unique |
| credits | Integer | |
| maxCapacity | Integer | |
| semester | String | |
| professor | Professor | ManyToOne |
| students | List\<Student\> | ManyToMany |

### AdmissionRecord
| Field | Type | Notes |
|-------|------|-------|
| id | Long | PK, Auto-generated |
| fees | Integer | |
| feesStatus | Enum | PAID, PARTIAL, UNPAID |
| admissionDate | LocalDate | |
| grade | String | |
| student | Student | OneToOne (owning side, FK: student_id) |

---

## 🔗 Entity Relationship Overview

```
Professor  ──< subjects >──  Subject  ──< students >──  Student
    │                                                       │
    └──────────────< ManyToMany >───────────────────────────┘
                                                            │
                                                    AdmissionRecord
                                                   (OneToOne, Cascade)
```

---

## 🚀 API Endpoints

### 🟢 Tier 1 — CRUD Foundations
> Basic persistence, entity creation, and retrieval

| Method | Endpoint | Description | JPA Concept |
|--------|----------|-------------|-------------|
| POST | `/professors` | Create a professor | `@Entity`, `@Id`, `@GeneratedValue` |
| GET | `/professors/{id}` | Get professor by ID | Basic repository lookup |
| PUT | `/professors/{id}` | Update professor details | Dirty checking |
| DELETE | `/professors/{id}` | Delete professor | Cascade behavior |
| POST | `/students` | Create a student | Enum field persistence |
| GET | `/students/{id}` | Get student by ID | Basic lookup |
| POST | `/subjects` | Create a subject | Basic CRUD |
| POST | `/admissions` | Create admission record for student | `@OneToOne` |

---

### 🟡 Tier 2 — Relationship Mappings & Assignments
> Demonstrates all JPA relationship types

| Method | Endpoint | Description | JPA Concept |
|--------|----------|-------------|-------------|
| PUT | `/professors/{id}/subjects/{subjectId}` | Assign subject to professor | `@ManyToOne` |
| PUT | `/subjects/{id}/enroll/{studentId}` | Enroll student into subject | `@ManyToMany` join table |
| PUT | `/students/{id}/professors/{professorId}` | Assign professor to student | `@ManyToMany` |
| GET | `/professors/{id}/students` | Get all students under a professor | Relationship traversal |
| GET | `/students/{id}/subjects` | Get all subjects a student is enrolled in | Relationship traversal |
| DELETE | `/students/{id}` | Delete student + cascade removes admission record | `CascadeType.ALL`, `orphanRemoval` |
| DELETE | `/subjects/{id}/students/{studentId}` | Unenroll student from subject | `orphanRemoval` on collection |

---

### 🟠 Tier 3 — Dynamic Query Methods & Filtering
> Derived query methods, `@Query`, JPQL

| Method | Endpoint | Description | JPA Concept |
|--------|----------|-------------|-------------|
| GET | `/students?status=ACTIVE` | Filter students by status | `findByStatus` |
| GET | `/professors?department=Engineering` | Filter professors by department | `findByDepartment` |
| GET | `/students?gpa=3.5` | Students with GPA above threshold | `findByGpaGreaterThan` |
| GET | `/subjects?semester=Fall2025` | Subjects by semester | `findBySemester` |
| GET | `/admissions?feesStatus=UNPAID` | All unpaid admission records | `findByFeesStatus` |
| GET | `/students/search?name=John` | Search students by partial name | `findByNameContainingIgnoreCase` |
| GET | `/professors/{id}/subjects/count` | Count subjects assigned to professor | Derived count query |

---

### 🔴 Tier 4 — Pagination, Sorting & Advanced Queries
> What separates junior devs from strong candidates

| Method | Endpoint | Description | JPA Concept |
|--------|----------|-------------|-------------|
| GET | `/students?page=0&size=10&sort=name,asc` | Paginated + sorted student list | `Pageable` |
| GET | `/professors?page=0&size=5&sort=salary,desc` | Paginated professors by salary | `Pageable` |
| GET | `/subjects?page=0&size=10&sort=credits,desc` | Paginated subjects by credits | `Pageable` |
| GET | `/students/top?limit=5` | Top 5 students by GPA | `@Query` with JPQL |
| GET | `/professors/department-summary` | Student + subject count by department | `@Query` aggregation |
| GET | `/admissions/overdue` | Students with unpaid fees past admission date | JPQL date comparison |
| GET | `/students/{id}/transcript` | Full profile with subjects, professor, admission record | FetchType, DTO projection |
| GET | `/subjects/available` | Subjects not yet at max capacity | Derived query + business logic |

---

## 📚 JPA Concepts Demonstrated

| Concept | Where Used |
|---------|------------|
| `@OneToOne` | Student ↔ AdmissionRecord |
| `@OneToMany` / `@ManyToOne` | Professor → Subject |
| `@ManyToMany` | Student ↔ Subject, Student ↔ Professor |
| `CascadeType.ALL` | Delete Student → deletes AdmissionRecord |
| `orphanRemoval = true` | Remove subject from student's list |
| `FetchType.LAZY` | Default for collections (performance) |
| `FetchType.EAGER` | Used selectively on AdmissionRecord |
| Derived Query Methods | `findByStatus`, `findByGpaGreaterThan`, etc. |
| `@Query` (JPQL) | Top students, department summary, overdue fees |
| `Pageable` + Sorting | All paginated list endpoints |
| Enum Persistence | `StudentStatus`, `FeesStatus` |
| DTO Projection | Transcript endpoint |

---

## 🗂️ Project Structure

```
src/
├── main/
│   ├── java/com/yourname/college/
│   │   ├── controller/
│   │   │   ├── ProfessorController.java
│   │   │   ├── StudentController.java
│   │   │   ├── SubjectController.java
│   │   │   └── AdmissionController.java
│   │   ├── entity/
│   │   │   ├── Professor.java
│   │   │   ├── Student.java
│   │   │   ├── Subject.java
│   │   │   └── AdmissionRecord.java
│   │   ├── enums/
│   │   │   ├── StudentStatus.java
│   │   │   └── FeesStatus.java
│   │   ├── repository/
│   │   │   ├── ProfessorRepository.java
│   │   │   ├── StudentRepository.java
│   │   │   ├── SubjectRepository.java
│   │   │   └── AdmissionRepository.java
│   │   ├── service/
│   │   │   ├── ProfessorService.java
│   │   │   ├── StudentService.java
│   │   │   ├── SubjectService.java
│   │   │   └── AdmissionService.java
│   │   ├── dto/
│   │   │   └── StudentTranscriptDTO.java
│   │   └── CollegeManagementApplication.java
│   └── resources/
│       └── application.properties
```

---

## ⚙️ Setup & Run

```bash
# Clone the repo
git clone https://github.com/thedarshannn/college-management-jpa.git
cd college-management-jpa

# Configure your DB in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/college_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

# Run
./mvnw spring-boot:run
```

---

## 📬 Sample Requests

```bash
# Create a professor
POST /professors
{
  "title": "Dr.",
  "firstName": "Alan",
  "lastName": "Turing",
  "email": "turing@college.com",
  "department": "Computer Science",
  "salary": 95000.00,
  "hireDate": "2020-08-01"
}

# Enroll a student into a subject
PUT /subjects/3/enroll/7

# Get paginated students sorted by GPA
GET /students?page=0&size=10&sort=gpa,desc

# Get top 5 students
GET /students/top?limit=5
```

---

## 🧠 Key Design Decisions

- **Lazy loading by default** on all collection relationships to avoid N+1 query issues
- **Cascade + orphanRemoval** on Student → AdmissionRecord ensures data integrity without manual cleanup
- **DTO projection** on the transcript endpoint to avoid over-fetching nested entities
- **Enum types** stored as strings (`@Enumerated(EnumType.STRING)`) for readability in the DB

---

## 👤 Author

**Darshan Prajapati**
- GitHub: [@thedarshannn](https://github.com/thedarshannn)
- LinkedIn: [darshan-prajapati29](https://linkedin.com/in/darshan-prajapati29)
- Portfolio: [darshan-agency.vercel.app](https://darshan-agency.vercel.app)
