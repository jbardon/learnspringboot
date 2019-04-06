package learnspringboot.core.dto.criteria;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductSearchCriteria {

    public List<String> make;

    public List<String> name;

    public Double minPrice;

    public Double maxPrice;
}
