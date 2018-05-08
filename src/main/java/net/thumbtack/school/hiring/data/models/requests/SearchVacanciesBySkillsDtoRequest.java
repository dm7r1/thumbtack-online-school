package net.thumbtack.school.hiring.data.models.requests;

import net.thumbtack.school.hiring.data.models.requests.base.AbstractExistingPersonDirectedDtoRequest;
import net.thumbtack.school.hiring.services.special.search.SearchOptions;

public class SearchVacanciesBySkillsDtoRequest extends AbstractExistingPersonDirectedDtoRequest {
    private Integer searchOptionCode;

    public SearchOptions getSearchOption() {
        return SearchOptions.getByCode(searchOptionCode);
    }

    public Integer getSearchOptionCode() {
        return searchOptionCode;
    }

    public void setSearchOptionCode(Integer searchOptionCode) {
        this.searchOptionCode = searchOptionCode;
    }
}
