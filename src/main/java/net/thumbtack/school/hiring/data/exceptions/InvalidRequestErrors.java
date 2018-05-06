package net.thumbtack.school.hiring.data.exceptions;

public enum InvalidRequestErrors {
    INVALID_LOGIN("invalid login string"),
    INVALID_PASSWORD("invalid password string"),
    INVALID_EMAIL("invalid email string"),
    INVALID_FIRST_NAME("invalid last name string"),
    INVALID_LAST_NAME("invalid first name string"),
    INVALID_PATRONYMIC("invalid patronymic string"),
    INVALID_COMPANY_NAME("invalid company name string"),
    INVALID_ADDRESS("invalid address string"),
    INVALID_TOKEN("invalid token"),
    NONEXISTENT_TOKEN("nobody has such a token"),
    INVALID_VACANCY_NAME("invalid vacancy name string"),
    INVALID_PAYMENT("invalid payment"),
    INVALID_REQUIREMENTS("invalid requirements"),
    NONEXISTENT_VACANCY("nonexistent employer uuid, vacancy number pair"),
    NONEXISTENT_VACANCY_REQUIREMENT_PAIR("this vacancy doesnt contain such requirement"),
    INVALID_SKILLS("invalid skills"),
    NONEXISTENT_SKILL("this employee doesnt have such skill")
    ;

    String description;
    InvalidRequestErrors(String description) {
        this.description = description;
    }
}