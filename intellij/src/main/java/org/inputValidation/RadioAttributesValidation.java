package org.inputValidation;

public class RadioAttributesValidation {

    public static void superValidateRadio(String radioAttribute) {
        if (radioAttribute.matches("(|)+}")){
            throw new IllegalArgumentException("Invalid name format");
        }

    }

    public static void validateRadioLanguage(String radioLanguage) {
        //superValidateRadio(radioLanguage);

        if(!radioLanguage.matches("[\\p{Lu}]{1,3}")) {
            throw new IllegalArgumentException("Invalid radio language attribute \""+radioLanguage+"\"\nYou can turn validation off in Advanced tab");
        }
    }

    public static void validateRadioBitrate(String radioBitrate) {
        //superValidateRadio(radioBitrate);

        if(!radioBitrate.matches("[0-9]{1,5}")) {
            throw new IllegalArgumentException("Invalid radio favorite attribute \""+radioBitrate+"\"\nYou can turn validation off in Advanced tab");
        }
    }

}
