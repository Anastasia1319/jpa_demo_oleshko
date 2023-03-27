/*----------CLEAR ALL
TRUNCATE users CASCADE;
TRUNCATE personal_infos CASCADE;
TRUNCATE employees CASCADE;
TRUNCATE departments CASCADE;
TRUNCATE students_sections CASCADE;
TRUNCATE sections CASCADE;
TRUNCATE students CASCADE;
*/

/*----------DROP ALL
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS personal_infos;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS students_sections;
DROP TABLE IF EXISTS sections;
DROP TABLE IF EXISTS students;
*/

CREATE TABLE IF NOT EXISTS personal_infos (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(75) NOT NULL,
    first_name VARCHAR(75) NOT NULL,
    last_name VARCHAR(75) NOT NULL,
    phone_number VARCHAR(75)
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(75) UNIQUE NOT NULL,
    "password" VARCHAR(75) NOT NULL,
    personal_info_id BIGINT REFERENCES personal_infos
);

CREATE TABLE IF NOT EXISTS departments (
    id BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(75) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(75) NOT NULL,
    last_name VARCHAR(75) NOT NULL,
    department_id BIGINT REFERENCES departments
);

CREATE TABLE IF NOT EXISTS sections (
    id BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(75) NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(75) NOT NULL
);

CREATE TABLE IF NOT EXISTS students_sections (
    student_id BIGINT REFERENCES students,
    section_id BIGINT REFERENCES sections
);
