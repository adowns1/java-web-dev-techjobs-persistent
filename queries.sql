## Part 1: Test it with SQL
   columns
    id INTEGER
    employer VARCHAR(20)
    name VARCHAR(20)
    skills VARCHAR(200)

## Part 2: Test it with SQL
    DROP TABLE job;

## Part 3: Test it with SQL
    DELETE FROM techjobs WHERE (job_id !== null)

## Part 4: Test it with SQL
    SELECT name, description
    FROM skills
    INNER JOIN job_skills on skills.id = job_skills.skills_id
    WHERE job_id is not null
    order by skill.name asc;