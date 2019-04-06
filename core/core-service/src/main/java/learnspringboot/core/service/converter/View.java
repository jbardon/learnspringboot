package learnspringboot.core.service.converter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum View {
    NO_VIEW(""),
    FULL("full");

    @Getter
    private String value;
}
