package se.inera.fmu.infrastructure.security;

import java.io.Serializable;

/**
 * @author andreaskaltenbach
 */
public class FakeCredentials implements Serializable {

    private String hsaId;
    private String fornamn;
    private String efternamn;
    private boolean lakare;
    private String enhetId;
    private Integer landstingCode;

    public FakeCredentials() {
    }

    public FakeCredentials(String hsaId, String fornamn, String efternamn, boolean lakare, String enhetId) {
        this.hsaId = hsaId;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.lakare = lakare;
        this.enhetId = enhetId;
    }

    public String getHsaId() {
        return hsaId;
    }

    public String getFornamn() {
        return fornamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public boolean isLakare() {
        return lakare;
    }

    public void setHsaId(String hsaId) {
        this.hsaId = hsaId;
    }

    public void setFornamn(String fornamn) {
        this.fornamn = fornamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public void setLakare(boolean lakare) {
        this.lakare = lakare;
    }

    public String getEnhetId() {
        return enhetId;
    }

    public void setEnhetId(String enhetId) {
        this.enhetId = enhetId;
    }
    
    public Integer getLandstingCode() {
		return landstingCode;
	}
    
    public void setLandstingCode(Integer landstingCode) {
		this.landstingCode = landstingCode;
	}

    @Override
    public String toString() {
        return "FakeCredentials{"
                + "hsaId='" + hsaId + '\''
                + ", fornamn='" + fornamn + '\''
                + ", efternamn='" + efternamn + '\''
                + ", lakare='" + lakare + '\''
                + ", enhetId='" + enhetId + '\''
                + ", landstingId='" + landstingCode + '\''
                + '}';
    }
}
