package com.realdolmen.hbo5.wasdapp.wasdappcore.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wasdapp_entry")
public class WasdappEntry implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String name;
    @Column(length = 64)
    private String locatie;
    @Column(length = 96)
    private String straat;
    @Column(length = 10)
    private String nummer;
    @Column(length = 6)
    private String postCode;
    @Column(length = 64)
    private String gemeente;
    @Column(length = 64)
    private String land;
    @Column(length = 2048)
    private String omschrijving;
    @Column(length = 64)
    private String wikiLink;
    @Column(length = 64)
    private String website;
    @Column(length = 64)
    private String telefoonNummer;
    @Column(length = 64)
    @Email
    private String email;
    @Column(length = 64)
    private Double prijs;
    @Column(length = 64)
    private String persoon;
    @Column(nullable = false)
    @NotNull
    private Timestamp aanmaakDatum;
    @NotNull
    @Column(nullable = false)
    private Timestamp wijzigDatum;
    @Column
    private Double lat;
    @Column
    private Double lon;

    public static WasdappEntryBuilder builder() {
        return new WasdappEntryBuilder();
    }

    public static final class WasdappEntryBuilder {

        private Long id;
        private String name;
        private String locatie;
        private String straat;
        private String nummer;
        private String postCode;
        private String gemeente;
        private String land;
        private String omschrijving;
        private String wikiLink;
        private String website;
        private String telefoonNummer;
        private String email;
        private Double prijs;
        private String persoon;
        private Timestamp aanmaakDatum;
        private Timestamp wijzigDatum;
        private Double lat;
        private Double lon;

        private WasdappEntryBuilder() {
        }

        public WasdappEntryBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public WasdappEntryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public WasdappEntryBuilder withOmschrijving(String omschrijving) {
            this.omschrijving = omschrijving;
            return this;
        }

        public WasdappEntryBuilder withLocatie(String locatie) {
            this.locatie = locatie;
            return this;
        }

        public WasdappEntryBuilder withStraat(String straat) {
            this.straat = straat;
            return this;
        }

        public WasdappEntryBuilder withNummer(String nummer) {
            this.nummer = nummer;
            return this;
        }

        public WasdappEntryBuilder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public WasdappEntryBuilder withGemeente(String gemeente) {
            this.gemeente = gemeente;
            return this;
        }

        public WasdappEntryBuilder withLand(String land) {
            this.land = land;
            return this;
        }

        public WasdappEntryBuilder withWikiLink(String wikiLink) {
            this.wikiLink = wikiLink;
            return this;
        }

        public WasdappEntryBuilder withWebsite(String website) {
            this.website = website;
            return this;
        }

        public WasdappEntryBuilder withTelefoonNummer(String telefoonNummer) {
            this.telefoonNummer = telefoonNummer;
            return this;
        }

        public WasdappEntryBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public WasdappEntryBuilder withPrijs(Double prijs) {
            this.prijs = prijs;
            return this;
        }

        public WasdappEntryBuilder withPersoon(String persoon) {
            this.persoon = persoon;
            return this;
        }

        public WasdappEntryBuilder withAanmaakDatum(Timestamp aanmaakDatum) {
            this.aanmaakDatum = aanmaakDatum;
            return this;
        }

        public WasdappEntryBuilder withWijzigDatum(Timestamp wijzigDatum) {
            this.wijzigDatum = wijzigDatum;
            return this;
        }

        public WasdappEntryBuilder withLat(Double lat) {
            this.lat = lat;
            return this;
        }

        public WasdappEntryBuilder withLon(Double lon) {
            this.lon = lon;
            return this;
        }

        public WasdappEntry build() {
            WasdappEntry wasdappEntry = new WasdappEntry();
            wasdappEntry.omschrijving = this.omschrijving;
            wasdappEntry.name = this.name;
            wasdappEntry.id = this.id;
            wasdappEntry.locatie = this.locatie;
            wasdappEntry.straat = this.straat;
            wasdappEntry.nummer = this.nummer;
            wasdappEntry.postCode = this.postCode;
            wasdappEntry.gemeente = this.gemeente;
            wasdappEntry.land = this.land;
            wasdappEntry.wikiLink = this.wikiLink;
            wasdappEntry.website = this.website;
            wasdappEntry.telefoonNummer = this.telefoonNummer;
            wasdappEntry.email = this.email;
            wasdappEntry.prijs = this.prijs;
            wasdappEntry.persoon = this.persoon;
            wasdappEntry.aanmaakDatum = this.aanmaakDatum;
            wasdappEntry.wijzigDatum = this.wijzigDatum;
            wasdappEntry.lat = this.lat;
            wasdappEntry.lon = this.lon;
            return wasdappEntry;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public String getPersoon() {
        return persoon;
    }

    public void setPersoon(String persoon) {
        this.persoon = persoon;
    }

    public Timestamp getAanmaakDatum() {
        return aanmaakDatum;
    }

    public void setAanmaakDatum(Timestamp aanmaakDatum) {
        this.aanmaakDatum = aanmaakDatum;
    }

    public Timestamp getWijzigDatum() {
        return wijzigDatum;
    }

    public void setWijzigDatum(Timestamp wijzigDatum) {
        this.wijzigDatum = wijzigDatum;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}
