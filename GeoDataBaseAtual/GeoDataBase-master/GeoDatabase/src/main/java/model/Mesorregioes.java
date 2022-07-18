
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "br_mesorregioes_2021")
public class Mesorregioes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    
    @Column(name = "cd_meso")
    private Integer cd_meso;
    
    @Column(name = "nm_meso")
    private String nm_meso;
    
    @Column(name = "sigla")
    private String sigla;
    
    @Column(name = "geom")
    private Geometry geometria;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getCd_meso() {
        return cd_meso;
    }

    public void setCd_meso(Integer cd_meso) {
        this.cd_meso = cd_meso;
    }

    public String getNm_meso() {
        return nm_meso;
    }

    public void setNm_meso(String nm_meso) {
        this.nm_meso = nm_meso;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Geometry getGeometria() {
        return geometria;
    }

    public void setGeometria(Geometry geometria) {
        this.geometria = geometria;
    }

    @Override
    public String toString() {
        return "Mesorregioes{" + "gid=" + gid + ", cd_meso=" + cd_meso + ", nm_meso=" + nm_meso + ", sigla=" + sigla + ", geometria=" + geometria + '}';
    }
    
    
}
