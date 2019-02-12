package com.realdolmen.hbo5.wasdapp.wasdappcore.dto;

public class WasdappEntryResponse {

    private String name;
    private String desciprion;

    public static WasdappEntryResponseBuilder builder(){
        return new WasdappEntryResponseBuilder();
    }

    public static final class WasdappEntryResponseBuilder {
        private String name;
        private String desciprion;

        private WasdappEntryResponseBuilder() {
        }

        public WasdappEntryResponseBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public WasdappEntryResponseBuilder withDesciprion(String desciprion) {
            this.desciprion = desciprion;
            return this;
        }

        public WasdappEntryResponse build() {
            WasdappEntryResponse wasdappEntryResponse = new WasdappEntryResponse();
            wasdappEntryResponse.desciprion = this.desciprion;
            wasdappEntryResponse.name = this.name;
            return wasdappEntryResponse;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desciprion;
    }
}
