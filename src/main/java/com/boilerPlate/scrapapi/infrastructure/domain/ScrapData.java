package com.boilerPlate.scrapapi.infrastructure.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScrapData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 //   @Column(columnDefinition = "comment '이름'")
    private String name;
  //  @Column(columnDefinition = "comment '종합소득금액'")
    private String cit;
   // @Column(columnDefinition = "comment '한해 국민연금 합산'")
    private String yearTotalNationalPension;
    //@Column(columnDefinition = "comment '한해 신용카소득공제 합산'")
    private String yearTotalCcid;
    //@Column(columnDefinition = "comment '년도'")
    private String thisYear;
    //@Column(columnDefinition = "comment '세액공제'")
    private String taxCredit;
}
