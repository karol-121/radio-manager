package org.inputValidation;

public class RadioAttributesValidation {

    public static void superValidateRadio(String radioAttribute) {
        if (radioAttribute.matches("(|)+}")){
            throw new IllegalArgumentException("Invalid name format");
        }

    }

    public static void validateRadioBitrate(String radioBitrate) {
        superValidateRadio(radioBitrate);

        if(!radioBitrate.matches("[0-9]{1,5}")) {
            throw  new IllegalArgumentException("Invalid radio favorite attribute");
        }
    }

}
