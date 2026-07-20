-- Departments
INSERT INTO department (name, code, description, created_at, updated_at)
VALUES
    ('Engineering', 'ENG', 'Software development and technical operations', NOW(), NOW()),
    ('Human Resources', 'HR', 'Employee relations and recruitment', NOW(), NOW()),
    ('Finance', 'FIN', 'Financial planning and accounting', NOW(), NOW()),
    ('Marketing', 'MKT', 'Brand management and digital marketing', NOW(), NOW()),
    ('Sales', 'SLS', 'Client acquisition and revenue generation', NOW(), NOW()),
    ('Operations', 'OPS', 'Day-to-day business operations', NOW(), NOW());

-- Employees (password is 'password123' for all)
INSERT INTO employee (first_name, last_name, email, password, role, department_id, position, phone, address, status, joining_date, created_at, updated_at)
VALUES
    ('Admin', 'User', 'admin@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'ADMIN', 1, 'System Administrator', '+1-555-0101', '123 Tech Street, Silicon Valley, CA', 'ACTIVE', '2024-01-01', NOW(), NOW()),
    ('John', 'Smith', 'john.smith@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'MANAGER', 1, 'Engineering Manager', '+1-555-0102', '456 Dev Avenue, San Francisco, CA', 'ACTIVE', '2024-02-15', NOW(), NOW()),
    ('Sarah', 'Johnson', 'sarah.johnson@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'HR', 2, 'HR Manager', '+1-555-0103', '789 HR Boulevard, Oakland, CA', 'ACTIVE', '2024-01-10', NOW(), NOW()),
    ('Michael', 'Williams', 'michael.williams@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'EMPLOYEE', 1, 'Senior Backend Developer', '+1-555-0104', '321 Code Lane, San Jose, CA', 'ACTIVE', '2024-03-01', NOW(), NOW()),
    ('Emily', 'Brown', 'emily.brown@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'EMPLOYEE', 1, 'Frontend Developer', '+1-555-0105', '654 UI Street, Palo Alto, CA', 'ACTIVE', '2024-03-15', NOW(), NOW()),
    ('David', 'Davis', 'david.davis@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'EMPLOYEE', 1, 'Full Stack Developer', '+1-555-0106', '987 Full Stack Road, Mountain View, CA', 'ACTIVE', '2024-04-01', NOW(), NOW()),
    ('Jessica', 'Miller', 'jessica.miller@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'EMPLOYEE', 1, 'QA Engineer', '+1-555-0107', '147 Quality Drive, Sunnyvale, CA', 'ACTIVE', '2024-04-15', NOW(), NOW()),
    ('Robert', 'Wilson', 'robert.wilson@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'MANAGER', 3, 'Finance Manager', '+1-555-0108', '258 Finance Way, San Mateo, CA', 'ACTIVE', '2024-02-01', NOW(), NOW()),
    ('Amanda', 'Moore', 'amanda.moore@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'EMPLOYEE', 3, 'Accountant', '+1-555-0109', '369 Accounting Plaza, Redwood City, CA', 'ACTIVE', '2024-03-20', NOW(), NOW()),
    ('Christopher', 'Taylor', 'christopher.taylor@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'MANAGER', 4, 'Marketing Manager', '+1-555-0110', '741 Marketing Blvd, Fremont, CA', 'ACTIVE', '2024-02-20', NOW(), NOW()),
    ('Jennifer', 'Anderson', 'jennifer.anderson@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'EMPLOYEE', 4, 'Digital Marketing Specialist', '+1-555-0111', '852 Digital Ave, Hayward, CA', 'ACTIVE', '2024-04-10', NOW(), NOW()),
    ('Daniel', 'Thomas', 'daniel.thomas@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'MANAGER', 5, 'Sales Manager', '+1-555-0112', '963 Sales Street, Berkeley, CA', 'ACTIVE', '2024-01-20', NOW(), NOW()),
    ('Lisa', 'Jackson', 'lisa.jackson@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'EMPLOYEE', 5, 'Sales Representative', '+1-555-0113', '147 Sales Road, Richmond, CA', 'ACTIVE', '2024-05-01', NOW(), NOW()),
    ('Matthew', 'White', 'matthew.white@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'MANAGER', 6, 'Operations Manager', '+1-555-0114', '258 Operations Lane, Daly City, CA', 'ACTIVE', '2024-02-10', NOW(), NOW()),
    ('Michelle', 'Harris', 'michelle.harris@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'EMPLOYEE', 6, 'Operations Coordinator', '+1-555-0115', '369 Coord Court, South San Francisco, CA', 'ACTIVE', '2024-05-15', NOW(), NOW()),
    ('Andrew', 'Martin', 'andrew.martin@operion.com', '$2a$12$HmyrN3Ly1BV9KrQnJ.Y5yepy6HfooH.2ea8d9oYbhbn0MVo.4aT76', 'EMPLOYEE', 1, 'DevOps Engineer', '+1-555-0116', '147 DevOps Drive, Santa Clara, CA', 'ACTIVE', '2024-06-01', NOW(), NOW());

-- Set variables for employee IDs based on email
SET @admin_id = (SELECT id FROM employee WHERE email = 'admin@operion.com');
SET @john_id = (SELECT id FROM employee WHERE email = 'john.smith@operion.com');
SET @sarah_id = (SELECT id FROM employee WHERE email = 'sarah.johnson@operion.com');
SET @michael_id = (SELECT id FROM employee WHERE email = 'michael.williams@operion.com');
SET @emily_id = (SELECT id FROM employee WHERE email = 'emily.brown@operion.com');
SET @david_id = (SELECT id FROM employee WHERE email = 'david.davis@operion.com');
SET @jessica_id = (SELECT id FROM employee WHERE email = 'jessica.miller@operion.com');
SET @robert_id = (SELECT id FROM employee WHERE email = 'robert.wilson@operion.com');
SET @amanda_id = (SELECT id FROM employee WHERE email = 'amanda.moore@operion.com');
SET @christopher_id = (SELECT id FROM employee WHERE email = 'christopher.taylor@operion.com');
SET @jennifer_id = (SELECT id FROM employee WHERE email = 'jennifer.anderson@operion.com');
SET @daniel_id = (SELECT id FROM employee WHERE email = 'daniel.thomas@operion.com');
SET @lisa_id = (SELECT id FROM employee WHERE email = 'lisa.jackson@operion.com');
SET @matthew_id = (SELECT id FROM employee WHERE email = 'matthew.white@operion.com');
SET @michelle_id = (SELECT id FROM employee WHERE email = 'michelle.harris@operion.com');
SET @andrew_id = (SELECT id FROM employee WHERE email = 'andrew.martin@operion.com');

-- Projects
INSERT INTO project (name, description, start_date, end_date, status, created_at, updated_at)
VALUES
    ('Operion Platform Redesign', 'Complete overhaul of the Operion platform with modern UI/UX', '2024-06-01', '2024-12-31', 'ACTIVE', NOW(), NOW()),
    ('Mobile App Development', 'Native mobile application for iOS and Android platforms', '2024-07-01', '2025-03-31', 'ACTIVE', NOW(), NOW()),
    ('API Integration Suite', 'Third-party API integrations for enhanced functionality', '2024-05-01', '2024-08-31', 'COMPLETED', NOW(), NOW()),
    ('Cloud Migration Project', 'Migration of on-premise infrastructure to cloud services', '2024-08-01', '2025-01-31', 'PLANNING', NOW(), NOW()),
    ('Security Audit & Enhancement', 'Comprehensive security review and implementation of best practices', '2024-04-01', '2024-06-30', 'CANCELLED', NOW(), NOW());

-- Project Members (using variables for employee IDs)
INSERT INTO project_member (project_id, employee_id, project_role, assigned_date, created_at, updated_at)
VALUES
    (1, @john_id, 'PROJECT_MANAGER', '2024-06-01', NOW(), NOW()),
    (1, @michael_id, 'BACKEND_DEVELOPER', '2024-06-01', NOW(), NOW()),
    (1, @emily_id, 'FRONTEND_DEVELOPER', '2024-06-01', NOW(), NOW()),
    (1, @david_id, 'BACKEND_DEVELOPER', '2024-06-15', NOW(), NOW()),
    (1, @jessica_id, 'QA_ENGINEER', '2024-06-15', NOW(), NOW()),
    (2, @john_id, 'PROJECT_MANAGER', '2024-07-01', NOW(), NOW()),
    (2, @michael_id, 'BACKEND_DEVELOPER', '2024-07-01', NOW(), NOW()),
    (2, @emily_id, 'FRONTEND_DEVELOPER', '2024-07-01', NOW(), NOW()),
    (2, @andrew_id, 'BACKEND_DEVELOPER', '2024-07-15', NOW(), NOW()),
    (3, @john_id, 'PROJECT_MANAGER', '2024-05-01', NOW(), NOW()),
    (3, @michael_id, 'BACKEND_DEVELOPER', '2024-05-01', NOW(), NOW()),
    (3, @david_id, 'BACKEND_DEVELOPER', '2024-05-01', NOW(), NOW()),
    (3, @jessica_id, 'QA_ENGINEER', '2024-05-15', NOW(), NOW()),
    (4, @john_id, 'PROJECT_MANAGER', '2024-08-01', NOW(), NOW()),
    (4, @andrew_id, 'BACKEND_DEVELOPER', '2024-08-01', NOW(), NOW());

-- Tasks (using variables for employee IDs)
INSERT INTO task (project_id, assigned_employee_id, title, description, priority, status, created_date, created_at, updated_at)
VALUES
    (1, @michael_id, 'Design new database schema', 'Create optimized database schema for the redesigned platform', 'HIGH', 'DONE', '2024-06-05', NOW(), NOW()),
    (1, @emily_id, 'Implement responsive UI components', 'Build reusable responsive UI components using modern frameworks', 'HIGH', 'IN_PROGRESS', '2024-06-10', NOW(), NOW()),
    (1, @david_id, 'Develop REST API endpoints', 'Create RESTful API endpoints for core functionality', 'HIGH', 'IN_PROGRESS', '2024-06-12', NOW(), NOW()),
    (1, @jessica_id, 'Write unit tests for API', 'Create comprehensive unit tests for all API endpoints', 'MEDIUM', 'TODO', '2024-06-20', NOW(), NOW()),
    (1, @michael_id, 'Implement authentication system', 'Build secure authentication and authorization system', 'HIGH', 'DONE', '2024-06-08', NOW(), NOW()),
    (1, @emily_id, 'Design dashboard layout', 'Create intuitive dashboard layout with data visualization', 'MEDIUM', 'IN_PROGRESS', '2024-06-15', NOW(), NOW()),
    (1, @david_id, 'Optimize database queries', 'Improve database performance through query optimization', 'MEDIUM', 'TODO', '2024-06-25', NOW(), NOW()),
    (1, @jessica_id, 'Perform integration testing', 'Conduct end-to-end integration testing', 'HIGH', 'TODO', '2024-06-28', NOW(), NOW()),
    (2, @michael_id, 'Design mobile app architecture', 'Create scalable architecture for mobile application', 'HIGH', 'DONE', '2024-07-05', NOW(), NOW()),
    (2, @emily_id, 'Implement iOS UI components', 'Build native iOS UI components following Apple guidelines', 'HIGH', 'IN_PROGRESS', '2024-07-10', NOW(), NOW()),
    (2, @andrew_id, 'Set up CI/CD pipeline', 'Configure automated build and deployment pipeline', 'MEDIUM', 'DONE', '2024-07-08', NOW(), NOW()),
    (2, @michael_id, 'Implement offline sync', 'Build offline data synchronization functionality', 'HIGH', 'TODO', '2024-07-20', NOW(), NOW()),
    (2, @emily_id, 'Implement Android UI components', 'Build native Android UI components following Material Design', 'HIGH', 'IN_PROGRESS', '2024-07-12', NOW(), NOW()),
    (3, @michael_id, 'Integrate payment gateway', 'Implement secure payment processing integration', 'HIGH', 'DONE', '2024-05-10', NOW(), NOW()),
    (3, @david_id, 'Implement notification service', 'Build push notification system', 'MEDIUM', 'DONE', '2024-05-15', NOW(), NOW()),
    (3, @jessica_id, 'Test API integrations', 'Validate all third-party API integrations', 'HIGH', 'DONE', '2024-05-20', NOW(), NOW()),
    (4, @andrew_id, 'Assess current infrastructure', 'Evaluate existing on-premise infrastructure', 'HIGH', 'TODO', '2024-08-05', NOW(), NOW()),
    (4, @john_id, 'Create migration plan', 'Develop detailed cloud migration strategy', 'HIGH', 'TODO', '2024-08-10', NOW(), NOW()),
    (4, @andrew_id, 'Set up cloud environment', 'Configure cloud infrastructure and services', 'HIGH', 'TODO', '2024-08-15', NOW(), NOW()),
    (1, @michael_id, 'Implement caching layer', 'Add Redis caching for improved performance', 'MEDIUM', 'TODO', '2024-07-01', NOW(), NOW());

-- Attendance Records (using variables for employee IDs)
INSERT INTO attendance (employee_id, date, clock_in, clock_out, status, created_at, updated_at)
VALUES
    (@michael_id, '2024-07-01', '2024-07-01 09:00:00', '2024-07-01 18:00:00', 'PRESENT', NOW(), NOW()),
    (@michael_id, '2024-07-02', '2024-07-02 09:15:00', '2024-07-02 18:30:00', 'LATE', NOW(), NOW()),
    (@michael_id, '2024-07-03', '2024-07-03 09:00:00', '2024-07-03 17:30:00', 'PRESENT', NOW(), NOW()),
    (@michael_id, '2024-07-04', '2024-07-04 09:00:00', '2024-07-04 18:00:00', 'PRESENT', NOW(), NOW()),
    (@michael_id, '2024-07-05', NULL, NULL, 'ABSENT', NOW(), NOW()),
    (@emily_id, '2024-07-01', '2024-07-01 08:45:00', '2024-07-01 17:45:00', 'PRESENT', NOW(), NOW()),
    (@emily_id, '2024-07-02', '2024-07-02 09:00:00', '2024-07-02 18:00:00', 'PRESENT', NOW(), NOW()),
    (@emily_id, '2024-07-03', '2024-07-03 09:30:00', '2024-07-03 13:00:00', 'HALF_DAY', NOW(), NOW()),
    (@emily_id, '2024-07-04', '2024-07-04 09:00:00', '2024-07-04 18:00:00', 'PRESENT', NOW(), NOW()),
    (@emily_id, '2024-07-05', '2024-07-05 09:00:00', '2024-07-05 18:00:00', 'PRESENT', NOW(), NOW()),
    (@david_id, '2024-07-01', '2024-07-01 09:00:00', '2024-07-01 18:00:00', 'PRESENT', NOW(), NOW()),
    (@david_id, '2024-07-02', '2024-07-02 09:00:00', '2024-07-02 18:00:00', 'PRESENT', NOW(), NOW()),
    (@david_id, '2024-07-03', NULL, NULL, 'ABSENT', NOW(), NOW()),
    (@david_id, '2024-07-04', '2024-07-04 09:00:00', '2024-07-04 18:00:00', 'PRESENT', NOW(), NOW()),
    (@david_id, '2024-07-05', '2024-07-05 09:00:00', '2024-07-05 18:00:00', 'PRESENT', NOW(), NOW()),
    (@jessica_id, '2024-07-01', '2024-07-01 09:00:00', '2024-07-01 18:00:00', 'PRESENT', NOW(), NOW()),
    (@jessica_id, '2024-07-02', '2024-07-02 09:00:00', '2024-07-02 18:00:00', 'PRESENT', NOW(), NOW()),
    (@jessica_id, '2024-07-03', '2024-07-03 09:00:00', '2024-07-03 18:00:00', 'PRESENT', NOW(), NOW()),
    (@jessica_id, '2024-07-04', '2024-07-04 09:00:00', '2024-07-04 18:00:00', 'PRESENT', NOW(), NOW()),
    (@jessica_id, '2024-07-05', '2024-07-05 09:00:00', '2024-07-05 18:00:00', 'PRESENT', NOW(), NOW());

-- Leave Requests (using variables for employee IDs)
INSERT INTO leave_request (employee_id, approved_by, leave_type, start_date, end_date, reason, status, created_date, created_at, updated_at)
VALUES
    (@michael_id, @john_id, 'ANNUAL', '2024-07-15', '2024-07-19', 'Family vacation', 'APPROVED', '2024-06-20', NOW(), NOW()),
    (@emily_id, @john_id, 'SICK', '2024-07-10', '2024-07-11', 'Medical appointment', 'APPROVED', '2024-07-09', NOW(), NOW()),
    (@david_id, @john_id, 'CASUAL', '2024-07-22', '2024-07-23', 'Personal matters', 'PENDING', '2024-07-08', NOW(), NOW()),
    (@jessica_id, @john_id, 'ANNUAL', '2024-08-01', '2024-08-10', 'Summer vacation', 'PENDING', '2024-07-05', NOW(), NOW()),
    (@michael_id, @john_id, 'SICK', '2024-06-15', '2024-06-16', 'Not feeling well', 'APPROVED', '2024-06-14', NOW(), NOW()),
    (@emily_id, @john_id, 'MATERNITY', '2024-08-15', '2024-11-15', 'Maternity leave', 'APPROVED', '2024-07-01', NOW(), NOW()),
    (@david_id, @john_id, 'UNPAID', '2024-07-25', '2024-07-26', 'Personal emergency', 'REJECTED', '2024-07-10', NOW(), NOW()),
    (@christopher_id, @sarah_id, 'ANNUAL', '2024-07-20', '2024-07-24', 'Travel plans', 'APPROVED', '2024-06-25', NOW(), NOW()),
    (@jennifer_id, @christopher_id, 'CASUAL', '2024-07-18', '2024-07-18', 'Family event', 'PENDING', '2024-07-12', NOW(), NOW()),
    (@daniel_id, @sarah_id, 'SICK', '2024-07-08', '2024-07-09', 'Doctor appointment', 'APPROVED', '2024-07-07', NOW(), NOW());