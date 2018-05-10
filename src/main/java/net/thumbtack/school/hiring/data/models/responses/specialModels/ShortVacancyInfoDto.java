package net.thumbtack.school.hiring.data.models.responses.specialModels;

import net.thumbtack.school.hiring.data.models.stored.Vacancy;

public class ShortVacancyInfoDto {
    private String vacancyName;
    private int payment;

    public static ShortVacancyInfoDto fromVacancy(Vacancy vacancy) {
        ShortVacancyInfoDto shortVacancyInfoDto = new ShortVacancyInfoDto();
        shortVacancyInfoDto.vacancyName = vacancy.getVacancyName();
        shortVacancyInfoDto.payment = vacancy.getPayment();

        return shortVacancyInfoDto;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
}
