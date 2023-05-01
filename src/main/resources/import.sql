INSERT INTO student (sid, name) VALUES ("G001", "Tom Higgins");
INSERT INTO student (sid, name) VALUES ("G002", "Mary Murphy");
INSERT INTO student (sid, name) VALUES ("G003", "Alan Fitzgibbon");
INSERT INTO student (sid, name) VALUES ("G004", "Bart O'Malley");
INSERT INTO student (sid, name) VALUES ("G005", "Alice Browne");

INSERT INTO lecturer (lid, name, tax_band, salary_scale) VALUES ("L001", "Alice O'Connor", "B1", 3);
INSERT INTO lecturer (lid, name, tax_band, salary_scale) VALUES ("L002", "William Jones", "C2", 2);
INSERT INTO lecturer (lid, name, tax_band, salary_scale) VALUES ("L003", "Tommy Kelly", "C2", 4);
INSERT INTO lecturer (lid, name, tax_band, salary_scale) VALUES ("L004", "Kate Robinson", "B2", 3);
INSERT INTO lecturer (lid, name, tax_band, salary_scale) VALUES ("L005", "Cathal Gibbons", "B2", 5);

INSERT INTO module (mid, name, credits, lecturer_id, level) VALUES ("JAV", "Java Programming", 10, 2, 7);
INSERT INTO module (mid, name, credits, lecturer_id, level) VALUES ("DB", "Databases", 5, 2, 8);
INSERT INTO module (mid, name, credits, lecturer_id, level) VALUES ("PM", "Project Management", 5, 2, 8);
INSERT INTO module (mid, name, credits, lecturer_id, level) VALUES ("MOB", "Mobile Applications", 5, 3, 7);
INSERT INTO module (mid, name, credits, lecturer_id, level) VALUES ("GRA", "Graphics", 5, 4, 7);
INSERT INTO module (mid, name, credits, lecturer_id, level) VALUES ("PRO", "Project & Dissertation", 15, NULL, 8);

INSERT INTO student_modules VALUES (1, 1);
INSERT INTO student_modules VALUES (1, 2);
INSERT INTO student_modules VALUES (1, 4);
INSERT INTO student_modules VALUES (1, 4);
INSERT INTO student_modules VALUES (2, 1);
INSERT INTO student_modules VALUES (3, 1);
INSERT INTO student_modules VALUES (3, 2);
INSERT INTO student_modules VALUES (3, 3);
