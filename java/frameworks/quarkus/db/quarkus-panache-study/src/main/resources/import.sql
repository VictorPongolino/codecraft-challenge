INSERT INTO discipline (id, name) VALUES
  (1, 'Mathematics'),
  (2, 'Physics'),
  (3, 'Chemistry'),
  (4, 'Biology'),
  (5, 'Computer Science'),
  (6, 'History'),
  (7, 'English'),
  (8, 'Art'),
  (9, 'Economics'),
  (10, 'Physical Education');

-- RANDOM Names.
INSERT INTO student (id, name, enroll) VALUES
  (1, 'John Doe', '2023-01-01'),
  (2, 'Jane Doe', '2023-01-02'),
  (3, 'Bob Smith', '2023-01-03'),
  (4, 'Alice Johnson', '2023-01-04'),
  (5, 'Charlie Brown', '2023-01-05'),
  (6, 'Emma White', '2023-01-06'),
  (7, 'David Miller', '2023-01-07'),
  (8, 'Sophia Davis', '2023-01-08'),
  (9, 'Michael Wilson', '2023-01-09'),
  (10, 'Olivia Martinez', '2023-01-10'),
  (11, 'Daniel Anderson', '2023-01-11'),
  (12, 'Mia Taylor', '2023-01-12'),
  (13, 'William Moore', '2023-01-13'),
  (14, 'Isabella Harris', '2023-01-14'),
  (15, 'Liam Jackson', '2023-01-15'),
  (16, 'Ava Thomas', '2023-01-16'),
  (17, 'Noah Garcia', '2023-01-17'),
  (18, 'Emily Clark', '2023-01-18'),
  (19, 'Ethan Lewis', '2023-01-19'),
  (20, 'Sofia Turner', '2023-01-20'),
  (21, 'Logan Hill', '2023-01-21'),
  (22, 'Aria Walker', '2023-01-22'),
  (23, 'James Ward', '2023-01-23'),
  (24, 'Aiden Reed', '2023-01-24'),
  (25, 'Ella Wright', '2023-01-25'),
  (26, 'Carter Brown', '2023-01-26'),
  (27, 'Amelia Cooper', '2023-01-27'),
  (28, 'Jackson Bailey', '2023-01-28'),
  (29, 'Madison Murphy', '2023-01-29'),
  (30, 'Lucas Stewart', '2023-01-30'),
  (31, 'Chloe Foster', '2023-02-01'),
  (32, 'Henry Butler', '2023-02-02'),
  (33, 'Grace Simmons', '2023-02-03'),
  (34, 'Landon Rivera', '2023-02-04'),
  (35, 'Zoe Perry', '2023-02-05'),
  (36, 'Gabriel Cox', '2023-02-06'),
  (37, 'Lily Russell', '2023-02-07'),
  (38, 'Nathan Alexander', '2023-02-08'),
  (39, 'Hannah Griffin', '2023-02-09'),
  (40, 'Elijah Price', '2023-02-10'),
  (41, 'Avery Myers', '2023-02-11'),
  (42, 'Scarlett Sanders', '2023-02-12'),
  (43, 'Owen Nichols', '2023-02-13'),
  (44, 'Addison Harper', '2023-02-14'),
  (45, 'Wyatt Kelly', '2023-02-15'),
  (46, 'Natalie Hayes', '2023-02-16'),
  (47, 'Andrew Simmons', '2023-02-17'),
  (48, 'Brooklyn Foster', '2023-02-18'),
  (49, 'Christopher Turner', '2023-02-19'),
  (50, 'Eva Mitchell', '2023-02-20'),
  (51, 'Jack Nelson', '2023-02-21'),
  (52, 'Peyton Henderson', '2023-02-22'),
  (53, 'Leo Gordon', '2023-02-23'),
  (54, 'Mila Harrison', '2023-02-24'),
  (55, 'Caleb Fisher', '2023-02-25'),
  (56, 'Madelyn Taylor', '2023-02-26'),
  (57, 'Isaac Perkins', '2023-02-27'),
  (58, 'Aubrey Long', '2023-02-27'),
  (59, 'Lincoln West', '2023-02-28'),
  (60, 'Alyssa Coleman', '2023-03-01'),
  (61, 'Max Turner', '2023-03-02'),
  (62, 'Aria Foster', '2023-03-03'),
  (63, 'Ezra Mitchell', '2023-03-04'),
  (64, 'Nova Hayes', '2023-03-05'),
  (65, 'Hudson Scott', '2023-03-06'),
  (66, 'Aurora Kelly', '2023-03-07'),
  (67, 'Caden Ward', '2023-03-08'),
  (68, 'Mila Peterson', '2023-03-09'),
  (69, 'Jaxon Dixon', '2023-03-10'),
  (70, 'Luna Brooks', '2023-03-11'),
  (71, 'Xander Hughes', '2023-03-12'),
  (72, 'Paisley Holmes', '2023-03-13'),
  (73, 'Kai Simmons', '2023-03-14'),
  (74, 'Skylar Harrison', '2023-03-15'),
  (75, 'Zane Turner', '2023-03-16'),
  (76, 'Isla Perry', '2023-03-17'),
  (77, 'Eli Fisher', '2023-03-18'),
  (78, 'Avery Nelson', '2023-03-19'),
  (79, 'Leah Griffin', '2023-03-20'),
  (80, 'Ryder Myers', '2023-03-21'),
  (81, 'Harper Mitchell', '2023-03-22'),
  (82, 'Finn Russell', '2023-03-23'),
  (83, 'Adalyn Henderson', '2023-03-24'),
  (84, 'Colton West', '2023-03-25'),
  (85, 'Evelyn Harrison', '2023-03-26'),
  (86, 'Maddox Cooper', '2023-03-27'),
  (87, 'Gianna Simmons', '2023-03-28'),
  (88, 'Nolan Hayes', '2023-03-29'),
  (89, 'Elise Scott', '2023-03-30'),
  (90, 'Rylan Turner', '2023-03-31');


INSERT INTO grade (grade, maxGrade, discipline_id)
SELECT ROUND(RAND() * 100) as grade, 100 as maxGrade, d.id as discipline_id  FROM discipline d ORDER BY RAND();

-- Allocate students to disciplines, ensuring each discipline has at least 10 students
INSERT INTO student_discipline (Student_id, discipline_id)
SELECT
  s.id AS Student_id,
  d.id AS Discipline_id
FROM
  student s
  CROSS JOIN discipline d
ORDER BY
  RAND(); -- Randomize the order of students and disciplines

-- Allocate grades to students, ensuring each student has at least one grade
INSERT IGNORE INTO student_grade (Student_id, grade_id) SELECT
s.id as Student_id,
g.id as grade_id
FROM
    student s
    JOIN grade g ON TRUE
WHERE
    NOT EXISTS (
        SELECT 1
        FROM student_grade sg
        WHERE sg.Student_id = s.id AND sg.grade_id = g.id
    )
ORDER BY RAND();