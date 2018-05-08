package net.thumbtack.school.hiring.data.models.requests.utils.validators;

import net.thumbtack.school.hiring.data.exceptions.InvalidRequestErrors;
import net.thumbtack.school.hiring.data.exceptions.InvalidRequestException;
import net.thumbtack.school.hiring.services.special.search.SearchOptions;

public class SearchRequestsValidator {
    public void validateSearchRequest(Integer searchOptionCode) throws InvalidRequestException {
        if(searchOptionCode == null || !isValidSearchOptionCode(searchOptionCode))
            throw new InvalidRequestException(InvalidRequestErrors.INVALID_SEARCH_OPTION);
    }

    public boolean isValidSearchOptionCode(Integer searchOptionCode) {
        return SearchOptions.isCodeValid(searchOptionCode);
    }
}
