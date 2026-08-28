CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(2048),
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    key VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    lead_id BIGINT REFERENCES users(id),
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE project_members (
    project_id BIGINT REFERENCES projects(id),
    user_id BIGINT REFERENCES users(id),
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (project_id, user_id)
);

CREATE TABLE issues (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES projects(id),
    issue_number INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50),
    assignee_id BIGINT REFERENCES users(id),
    reporter_id BIGINT REFERENCES users(id),
    parent_issue_id BIGINT REFERENCES issues(id),
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP,
    due_date DATE,
    UNIQUE (project_id, issue_number)
);

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    issue_id BIGINT NOT NULL REFERENCES issues(id),
    author_id BIGINT NOT NULL REFERENCES users(id),
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP
);

CREATE TABLE labels (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES projects(id),
    name VARCHAR(100) NOT NULL,
    color VARCHAR(20)
);

CREATE TABLE issue_labels (
    issue_id BIGINT REFERENCES issues(id),
    label_id BIGINT REFERENCES labels(id),
    PRIMARY KEY (issue_id, label_id)
);

CREATE TABLE sprints (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES projects(id),
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE
);

CREATE TABLE attachments (
    id BIGSERIAL PRIMARY KEY,
    issue_id BIGINT NOT NULL REFERENCES issues(id),
    file_url VARCHAR(500) NOT NULL,
    file_name VARCHAR(255),
    uploaded_by BIGINT REFERENCES users(id),
    created_at TIMESTAMP DEFAULT now()
);
