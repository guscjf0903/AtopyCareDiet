package org.api.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.core.dto.DiseasePostDto;

@Entity
@Table(name = "disease", schema = "atopycare_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiseaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Long diseaseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "record_date", unique = true)
    private Date diseaseDate;

    @Column(name = "disease_stage")
    private int diseaseStage;

    @Column(name = "remark")
    private String diseaseRemark;

    public DiseaseEntity(UserEntity user, Date diseaseDate, int diseaseStage, String diseaseRemark) {
        this.user = user;
        this.diseaseDate = diseaseDate;
        this.diseaseStage = diseaseStage;
        this.diseaseRemark = diseaseRemark;
    }

    public static DiseaseEntity of(UserEntity user, DiseasePostDto diseasePostDto) {
        return new DiseaseEntity(user, diseasePostDto.getDiseaseDate(), diseasePostDto.getDiseaseStep(), diseasePostDto.getDiseaseRemark());
    }
}

