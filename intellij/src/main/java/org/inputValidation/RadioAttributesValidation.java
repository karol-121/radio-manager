package org.inputValidation;

public class RadioAttributesValidation {

    public static void superValidateRadio(String radioAttribute) {
        if (radioAttribute.matches("(|)+}")){
            throw new IllegalArgumentException("Invalid name format");
        }

    }

    public static void validateRadioLanguage(String radioLanguage) {
        superValidateRadio(radioLanguage);

        if(!radioLanguage.matches("[\\p{Lu}]{0,3}")) {
            throw new IllegalArgumentException("Invalid radio language attribute");
        }
    }

    public static void validateRadioBitrate(String radioBitrate) {
        superValidateRadio(radioBitrate);

        if(!radioBitrate.matches("[0-9]{0,5}")) {
            throw new IllegalArgumentException("Invalid radio favorite attribute");
        }
    }

}
