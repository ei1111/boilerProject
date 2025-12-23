# spring-boot-security-api

본 프로젝트는 **Spring Boot 3.x 기반 API 서버**로,  
보안, 암호화, 모니터링, API 문서화를 함께 학습·적용한 프로젝트입니다.

---

## 기술 스택

### Backend
- **Java:** 17
- **Spring Boot:** 3.2.3
- **Spring Data JPA**
- **Spring Security**

### Database
- **H2 Database** (In-Memory)

### Monitoring & Logging
- **Spring Actuator**
- **Prometheus**
- **Grafana**
- **P6Spy** (SQL 로그)

### Query
- **QueryDSL 5.0.0 (Jakarta)**

### Security
- **JWT 인증**
- **AES-256 개인정보 암호화**

### API Documentation
- **Swagger (SpringDoc OpenAPI)**

---

## 주요 기능

- 사용자 인증 및 권한 처리 (Spring Security + JWT)
- 개인정보 암호화 저장 (AES-256)
- JPA + QueryDSL 기반 데이터 조회
- OpenFeign을 이용한 외부 API 연동
- 캐시 적용을 통한 조회 성능 개선
- SQL 로그 및 애플리케이션 메트릭 수집

---

## 모니터링

- Actuator를 통한 애플리케이션 상태 확인
- Prometheus 메트릭 수집
- Grafana 대시보드 연동 가능

---

## API 테스트

- Swagger UI를 통해 API 테스트가 가능합니다.
- http://localhost:8080/swagger-ui/index.html
