# 🗓️ CASS (다중 유저 그룹 스케줄러 앱)
> **풀스택(AWS/PHP/MySQL + Android) 아키텍처 기반 스케줄링 애플리케이션**

다수의 유저가 그룹을 형성하고 일정을 공유하며 상호작용할 수 있는 안드로이드 애플리케이션입니다. 클라이언트 렌더링뿐만 아니라, 클라우드 인프라(AWS) 환경에서 RDBMS를 설계하고 서버 API를 직접 구축하며 전체 시스템의 데이터 흐름을 완성한 프로젝트입니다.

## 📱 앱 화면 (Screenshots)
<!-- 💡 아래 줄을 지우고 PPT에 있는 '앱 실행 화면 이미지'들을 드래그 앤 드롭 하세요! -->
<img width="1010" height="568" alt="image" src="https://github.com/user-attachments/assets/a9413c87-9270-418e-aae4-cef3be25c35c" />
<img width="1009" height="566" alt="image" src="https://github.com/user-attachments/assets/d947dd8b-54a8-480a-bd18-3fb0c8992193" />
<img width="1010" height="566" alt="image" src="https://github.com/user-attachments/assets/5cbb0457-0de6-41a4-b2fc-1e6a9824a492" />
<img width="1010" height="568" alt="image" src="https://github.com/user-attachments/assets/ce626256-b3f4-4d06-80d9-34a4760bdc83" />
<img width="1006" height="563" alt="image" src="https://github.com/user-attachments/assets/363992bb-f716-45b1-8f4f-abc00a8a9544" />
<img width="1009" height="571" alt="image" src="https://github.com/user-attachments/assets/ef45a8a4-5f50-4e85-9085-225d32438ee2" />
<img width="1007" height="564" alt="image" src="https://github.com/user-attachments/assets/4237c4f1-af1b-4c55-b7b8-fa195fd25a17" />

<br>

## ⚙️ 아키텍처 및 DB 구조 (Architecture & ERD)

**1. 시스템 흐름도 (FLOW)**
<img width="1010" height="569" alt="image" src="https://github.com/user-attachments/assets/56080d22-2d97-479e-971a-e2656970b96e" />


**2. 데이터베이스 모델링 (ERD)**
<img width="1007" height="561" alt="image" src="https://github.com/user-attachments/assets/326134ee-9563-455f-8921-fe793f80b29a" />


<br>

## 🛠 기술 스택 (Tech Stack)
- **Client (Android):** Java, Android Studio
- **Server/Infrastructure:** AWS (EC2), PHP
- **Database:** MySQL
- **Version Control:** GitHub

<br>

##  주요 기능 (Key Features)
- **회원가입 및 로그인:** 유저 인증 및 세션 관리
- **그룹 기능:** 다수의 유저가 참여할 수 있는 스케줄 그룹 생성 및 관리
- **친구 초대:** 시스템 내 유저 검색 및 그룹 스케줄 초대
- **스케줄 관리 (CRUD):** 일정 생성, 조회, 수정, 삭제 및 참여자 간 동기화

<br>

##  나의 핵심 역할 (What I Did)
**팀장 (앱 기획, 클라이언트/서버 전반 구조 설계 및 핵심 기능 개발)**

1. **사용자 스토리보드 및 기획 총괄:** 앱의 전체적인 화면 흐름(스토리보드)을 기획하여 사용자 친화적인 동선을 설계했습니다.
2. **프론트엔드 코어 기능 개발:** 로그인/회원가입, 스케줄 생성, 친구 초대 등 사용자와 맞닿아 있는 핵심 클라이언트 로직을 직접 구현했습니다.
3. **AWS 인프라 및 DB 세팅:** AWS 환경을 직접 세팅하고, 다중 유저 환경에 적합한 관계형 데이터베이스(MySQL) 테이블을 설계 및 운용했습니다.
4. **프로젝트 최종 발표:** 개발 결과물의 기술적 성과와 아키텍처 구조를 명확하게 전달하는 발표를 진행했습니다.

<br>

##  기술적 고민

### 1. JSON 기반 RESTful API 설계와 클라이언트-서버 통신 체화
- 문제: 안드로이드 프론트엔드를 넘어 처음으로 서버(PHP)를 직접 구축해야 했기에, 클라이언트와 서버가 데이터를 안전하게 주고받는 규격을 확립하는 것이 가장 큰 과제였습니다.
- 해결 : 데이터 교환 포맷으로 **JSON**을 채택하고, HTTP 통신 방식(GET/POST 등)에 맞춰 직접 데이터의 송수신(Get/Set) 로직을 설계했습니다. 백엔드의 구조를 직접 설계해 본 이 경험은, 이후 클라이언트 개발자로서 서버 개발자의 API 명세서 의도를 빠르고 정확하게 파악할 수 있는 좋은 경험이 되었습니다.

### 2. 기획(스토리보드)의 중요성 체화 및 스케줄링
- 문제: 첫 팀 프로젝트였던 만큼, 초기 기획 단계에서 고려하지 못한 엣지 케이스들이 발생하여 개발 중간에 진행이 지체되는 구간이 있었습니다.
- 해결: 팀원들과의 적극적인 소통과 우선순위 재조정을 통해 기존에 목표했던 핵심 기능들은 무사히 구현해 냈습니다. 이 과정을 통해 코드를 작성하기 전 스토리보드와 구조 설계가 얼마나 탄탄해야 하는지 뼈저리게 깨달았고, 이후 프로젝트에서 소프트웨어 아키텍처를 꼼꼼하게 사전 검증하는 습관을 들이게 되었습니다.
