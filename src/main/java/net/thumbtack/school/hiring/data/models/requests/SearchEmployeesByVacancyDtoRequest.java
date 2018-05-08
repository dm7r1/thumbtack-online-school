package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;
import net.thumbtack.school.hiring.services.special.search.SearchOptions;

public class SearchEmployeesByVacancyDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private Integer vacancyNumber;
    private Integer searchOptionCode;

    public Integer getSearchOptionCode() {
        return searchOptionCode;
    }

    public SearchOptions getSearchOption() {
        return SearchOptions.getByCode(searchOptionCode);
    }

    public void setSearchOptionCode(Integer searchOptionCode) {
        this.searchOptionCode = searchOptionCode;
    }

    public Integer getVacancyNumber() {
        return vacancyNumber;
    }

    public void setVacancyNumber(Integer vacancyNumber) {
        this.vacancyNumber = vacancyNumber;
    }
}
