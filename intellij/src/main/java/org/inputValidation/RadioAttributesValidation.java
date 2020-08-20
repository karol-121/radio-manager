package org.inputValidation;

public class RadioAttributesValidation {

    //this validation can be simplified but it does work for now.

    private static void checkDelimiter(String string) {
        if (string.matches(".*\\|+")){
            throw new IllegalArgumentException("Input contains illegal character \"|\"");
        }

    }

    private static void checkBasic(String string) {
        if (string.isBlank()) {
            throw new IllegalArgumentException("Input can not be empty");
        }
        if (string.length() > 60) {
            throw new IllegalArgumentException("Input value is too long");
        }

    }

    public static void superValidateRadio(String radioAttribute) {
        checkBasic(radioAttribute);
        checkDelimiter(radioAttribute);

    }

    //not used anymore but im not sure if i want to implement them back
    public static void validateRadioLanguage(String radioLanguage) {
        checkBasic(radioLanguage);

        if(!radioLanguage.matches("[\\p{Lu}]{1,3}")) {
            throw new IllegalArgumentException("Invalid radio language attribute");
        }
    }

    //not used anymore but im not sure if i want to implement them back
    public static void validateRadioBitrate(String radioBitrate) {
        checkBasic(radioBitrate);

        if(!radioBitrate.matches("[0-9]{1,5}")) {
            throw new IllegalArgumentException("Invalid radio bitrate attribute");
        }
    }

}
