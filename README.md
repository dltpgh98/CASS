# 📍 BBIC (친구와 함께하는 길찾기 앱)
> **🏆 학과 졸업과제 최우수상 및 교내 전체 우수상 동시 수상작**

약속 장소까지 가는 길, "너 어디쯤이야?"라는 연락 없이 서로의 실시간 위치를 지도에서 확인하고 함께 길을 찾을 수 있는 위치 기반 소셜 네트워킹 안드로이드 애플리케이션입니다.

## 📱 앱 화면 (Screenshots)

<img width="134" height="268" alt="image" src="https://github.com/user-attachments/assets/2a13fc1c-3119-4e52-bffd-5f4d07474eff" />

<img width="134" height="268" alt="image" src="https://github.com/user-attachments/assets/c3c51ea1-ae6f-4750-b141-15067fe46d47" />
<img width="134" height="268" alt="image" src="https://github.com/user-attachments/assets/4da53112-93b8-487a-a188-5e5a1da9b557" />
<img width="134" height="268" alt="image" src="https://github.com/user-attachments/assets/0fed0334-febf-43c9-b4c6-dd471c705869" />
<img width="134" height="268" alt="image" src="https://github.com/user-attachments/assets/607261da-ae72-4843-8215-773898d29ac3" />

<img width="134" height="268" alt="image" src="https://github.com/user-attachments/assets/b7c20bc1-6f2f-484c-aaa1-3a0e13692e05" />
<img width="134" height="269" alt="image" src="https://github.com/user-attachments/assets/02b9ea29-7775-4104-9ae9-aa35a80f1a6f" />

<img width="135" height="268" alt="image" src="https://github.com/user-attachments/assets/188b9374-dec4-4b02-a200-292708d7fd13" />
<img width="134" height="268" alt="image" src="https://github.com/user-attachments/assets/0724d408-e2b0-40b5-8cd7-d193e24e0680" />

<img width="135" height="268" alt="image" src="https://github.com/user-attachments/assets/cf078e37-06f7-4004-a293-0287bada1884" />


<br>

## ⚙️ 시스템 기능 및 아키텍처

<img width="887" height="302" alt="image" src="https://github.com/user-attachments/assets/3f85e872-750c-4ee5-a699-9e62c3ea3c8c" />


<br>

## 🛠 기술 스택 (Tech Stack)
- **Client (Android):** Java, Android Studio, Fragment
- **Backend & DB:** PHP, MySQL
- **API:** Google Maps API (지도 시각화 및 길찾기)
- **Collaboration:** GitHub

<br>

## ✨ 주요 기능 (Key Features)
- **약속 생성 및 친구 초대:** 날짜와 목적지를 설정하여 약속을 생성하고, 앱 내 친구들을 초대할 수 있습니다.
- **실시간 GPS 위치 공유:** 약속을 수락한 친구들 간에 지도 위에서 실시간으로 서로의 위치를 확인할 수 있습니다.
- **위치 숨기기 토글 기능:** 프라이버시 보호를 위해 유저가 원할 때 언제든 자신의 위치를 타인에게 숨길 수 있습니다.
- **경로 안내 및 장소 검색:** 외부 API를 연동하여 목적지까지의 길찾기, 지하철 노선도 확인, 장소 검색 기능을 지원합니다.
- **소셜 로그인:** 간편한 가입 및 로그인을 위한 소셜 로그인 기능을 제공합니다.

<br>

## 👨‍💻 나의 역할 (What I Did)
**팀장 (4인 팀 / 기획, DB 관리, 핵심 로직 개발 및 최종 발표 담당)**

1. **전체 스토리보드 및 기획 주도:** 사용자 경험(UX) 중심의 화면 흐름도를 설계하고, 이에 맞춘 전반적인 데이터 구조(MySQL)를 생성 및 관리했습니다.
2. **핵심 소셜 및 위치 동기화 로직 개발:** '친구 추가' 기능 및 약속에 초대된 유저들 간의 GPS 실시간 위치 공유 기능을 구현했습니다.
3. **버전 관리 및 스케줄링:** GitHub을 활용해 팀원들의 코드를 병합하고, 클라이언트와 서버 간의 병목 현상을 방지하기 위해 매일 작업 우선순위를 조율했습니다.

<br>

##  기술적 고민과 트러블슈팅 (Troubleshooting)


###  Fragment 중심 개발의 한계 경험과 인사이트
- **도전:** '친구 목록' 및 화면 전환 기능을 여러 개의 Fragment로 나누어 구현하는 과정에서, 생명주기(Lifecycle) 충돌과 UI 상태 관리의 복잡도가 기하급수적으로 높아지는 것을 경험했습니다.
- **인사이트:** 이 당시 느꼈던 레거시 뷰(XML)와 Fragment의 한계점은, 이후 제가 선언형 UI인 **Jetpack Compose**를 깊게 연구하고 모던 안드로이드 아키텍처로 기술 스택을 완벽하게 전환하게 된 가장 결정적인 원동력이 되었습니다.

<br>

## 🎯 배운 점 (Lessons Learned)
팀장으로서 역할을 명확히 분담하더라도, 클라이언트와 서버 간의 긴밀한 의존성 때문에 병목(Bottleneck) 현상이 발생할 수 있음을 뼈저리게 경험했습니다. 이를 극복하기 위해 매일 팀원들과 소통하며 작업의 우선순위를 조율했고, 견고한 프로젝트 스케줄 관리의 중요성을 깨달았습니다. 성공적인 팀워크를 바탕으로 학과 최우수상 및 교내 우수상이라는 객관적인 성과를 이루어낼 수 있었습니다.
