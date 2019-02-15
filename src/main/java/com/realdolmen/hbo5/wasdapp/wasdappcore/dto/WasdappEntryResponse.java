package com.realdolmen.hbo5.wasdapp.wasdappcore.dto;

public class WasdappEntryResponse {

    private String name;
    private String description;

    public static WasdappEntryResponseBuilder builder(){
        return new WasdappEntryResponseBuilder();
    }

    public static final class WasdappEntryResponseBuilder {
        private String name;
        private String description;

        private WasdappEntryResponseBuilder() {
        }

        public WasdappEntryResponseBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public WasdappEntryResponseBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public WasdappEntryResponse build() {
            WasdappEntryResponse wasdappEntryResponse = new WasdappEntryResponse();
            wasdappEntryResponse.description = this.description;
            wasdappEntryResponse.name = this.name;
            return wasdappEntryResponse;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
