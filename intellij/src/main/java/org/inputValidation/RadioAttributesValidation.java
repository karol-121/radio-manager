package org.inputValidation;

public class RadioAttributesValidation {

    public static void superValidateRadio(String radioAttribute) {
        if (radioAttribute.matches("(|)+}")){
            throw new IllegalArgumentException("Invalid input information");
        }

    }

    public static void validateIsEmpty(String radioAttribute) {
        superValidateRadio(radioAttribute);
        if (radioAttribute.isEmpty()) {
            throw new IllegalArgumentException("Input can not be empty");
        }
    }

    public static void validateRadioLanguage(String radioLanguage) {
        superValidateRadio(radioLanguage);

        if(!radioLanguage.matches("[\\p{Lu}]{1,3}")) {
            throw new IllegalArgumentException("Invalid radio language attribute \""+radioLanguage+"\"\nYou can turn validation off in Advanced tab");
        }
    }

    public static void validateRadioBitrate(String radioBitrate) {
        superValidateRadio(radioBitrate);

        if(!radioBitrate.matches("[0-9]{1,5}")) {
            throw new IllegalArgumentException("Invalid radio bitrate attribute \""+radioBitrate+"\"\nYou can turn validation off in Advanced tab");
        }
    }

}
