# Spring PetClinic - Cloud Native Migration

Spring 모놀리식 애플리케이션을 클라우드 네이티브 구조로 단계별 전환한 샘플 프로젝트입니다.

## 프로젝트 목표

레거시 Spring Boot 모놀리식 앱을 단계별로 클라우드 환경에 맞게 전환하며,
각 단계별 아키텍처 변화와 전환 과정을 기록합니다.

## 전환 단계

| 브랜치 | 단계 | 설명 |
|--------|------|------|
| `01-monolith` | 모놀리식 | Spring Boot + H2 로컬 실행 |
| `02-docker` | 컨테이너화 | Dockerfile 작성, Docker 이미지 빌드 |
| `03-cicd` | CI/CD | GitHub Actions 자동 빌드 + Docker Hub 푸시 |
| `04-aws` | 클라우드 배포 | AWS EC2 + Docker 컨테이너 배포 |
| `05-msa` | MSA 분리 | 서비스별 독립 배포 구조 (진행 중) |

## 아키텍처

### Before (레거시)
```
로컬 PC
└── Spring Boot App (8080)
    └── H2 In-memory DB
```

### After (클라우드 네이티브)
```
GitHub (코드 push)
    ↓ 자동 트리거
GitHub Actions
    ↓ 빌드 + 이미지 생성
Docker Hub (이미지 저장)
    ↓ pull
AWS EC2 (13.220.3.217:8080)
    └── Docker Container
        └── Spring Boot App
```

## 단계별 스크린샷

### 1단계 - 로컬 실행 (localhost:8080)
![localhost8080](https://github.com/user-attachments/assets/02d4b60e-59aa-44df-aa36-13e8f1275a16)

### 2단계 - Docker 컨테이너 실행 (localhost:8081)
![AWS배포](https://github.com/user-attachments/assets/dab17933-656a-425c-9580-b7baf7e8c51f)

### 3단계 - GitHub Actions CI/CD 성공
<img width="1377" height="884" alt="githubActions" src="https://github.com/user-attachments/assets/9517f745-7a37-42cc-ab0b-2d159f7ac4c9" />

### 3단계 - Docker Hub 이미지 업로드
<img width="2353" height="436" alt="dockerHub" src="https://github.com/user-attachments/assets/70fb9b79-e9c2-4003-b48c-adf771b2f11e" />

### 4단계 - AWS EC2 배포 (13.220.3.217:8080)
![localhost8081](https://github.com/user-attachments/assets/3313d6e9-2a7a-4af4-9577-66e01b16dbbb)

## 기술 스택

- **Backend**: Java 17, Spring Boot 4.0
- **Container**: Docker
- **CI/CD**: GitHub Actions
- **Registry**: Docker Hub
- **Cloud**: AWS EC2

## 로컬 실행
```bash
# 1. 일반 실행
./mvnw spring-boot:run

# 2. Docker로 실행
docker build -t spring-petclinic .
docker run -p 8080:8080 spring-petclinic
```

## 배포

GitHub Actions를 통해 `main` 브랜치에 push하면 자동으로 빌드 및 Docker Hub에 업로드됩니다.
```bash
# AWS EC2에서 실행
docker pull gunho30811/spring-petclinic:latest
docker run -d -p 8080:8080 gunho30811/spring-petclinic:latest
```

## 접속 URL

- 로컬: http://localhost:8080
- Docker: http://localhost:8081
- AWS: http://13.220.3.217:8080
