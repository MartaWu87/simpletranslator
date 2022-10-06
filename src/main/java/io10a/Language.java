package io10a;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Language {


    @NotBlank(message = "Put country code")
    @Pattern(regexp = "[a-z]{2}")
    private String name;
    @NotBlank(message = "Put the greeting")
    @Pattern(regexp = "[^0-9]+")
    private String value;

    public Language(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Language() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
