<h1 align="center">
  익명 게시판 (Spring Boot 프로젝트)
</h1>
<p align="center">익명으로 글을 작성할 수 있는 간단한 게시판</p>
<p align="center">
  원하는 아이디 혹은 닉네임과 비밀번호를 적고 게시글을 작성할 수 있으며<br>
  그때 설정된 비밀번호를 해당 게시글의 수정 및 삭제에 활용
</p>

---

## key Features

<br>

- 게시글 번호, 제목, 작성자, 조회수, 작성일자 등 게시물 정보를 한 눈에 알 수 있도록 구성

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/e21876c6-fc55-42c4-a9bd-b4a659bc664a" width="800">
</div>

<br>

- 특정 게시글을 간편하게 찾을 수 있도록 검색 기능 구현

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/b3fafb59-118d-4170-9e6d-fe1c15740471" width="800">
  <img src="https://github.com/user-attachments/assets/1d8a83e3-f06b-4edb-a5ed-344dfb177b98" width="800">
</div>

<br>

- 게시글 수정 및 삭제할 때는 해당 게시글을 작성할 때 설정한 비밀번호를 입력해서 일치하면 가능하도록 함

<br>

1. 게시글 수정

<p align="center">[수정 전]</p>

<div align="center">
  <img src="https://github.com/user-attachments/assets/3fd98157-d096-4024-bfe9-3057d674c073" width="600">
</div>

<br>

<p align="center">[정보를 수정하고 해당 게시글의 비밀번호 입력]</p>

<div align="center">
  <img src="https://github.com/user-attachments/assets/02da5986-4695-490f-acac-9a27e39437c4" width="600">
</div>

<br><br>

2. 게시글 삭제

<p align="center">[삭제할 게시글의 비밀번호 입력]</p>

<div align="center">
  <img src="https://github.com/user-attachments/assets/0a147780-7379-4933-a214-9f7d144b56ad" width="600">
</div>

<br>

## other components

<br>

- 게시글 작성

<br>

<p align="center">[작성 전]</p>
<div align="center">
  <img src="https://github.com/user-attachments/assets/dca9d40b-52d8-404a-9422-34dca1e7cf07" width="600">
</div>

<br>

<p align="center">[작성 후]</p>
<div align="center">
  <img src="https://github.com/user-attachments/assets/e18fd275-7647-48ee-84f8-98d61d51f57e" width="600">
</div>

<br><br>

- 게시글 상세 확인

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/83d7193c-8539-4309-b170-cabd8274d7d0" width="900">
</div>

<br>

<h2>Built With</h2>
<br>

<div>
  <img src="https://github.com/user-attachments/assets/9e352e71-414a-4e36-a9ee-2d92e06b6678">
  <img src="https://github.com/user-attachments/assets/ef0fb4e9-083c-4b86-b97a-4b4f8c872fbe">
  <img src="https://github.com/user-attachments/assets/7b274529-cdea-4ee4-a4a8-fa9de36cb063">
  <img src="https://github.com/user-attachments/assets/aba6313f-bf26-46fb-825b-9f3acf68433d">
  <img src="https://github.com/user-attachments/assets/b1f01c91-93d4-4807-bfa5-7033932116f4">
  <img src="https://github.com/user-attachments/assets/be3cd3ae-a049-41a5-96ab-49054c38c8f5"> 
  <img src="https://github.com/user-attachments/assets/0ef37a0d-1dbf-4ffc-b24b-33213554fcf6">  
  <img src="https://github.com/user-attachments/assets/5bfbbf40-ef9b-4f6d-a429-f46e19d8065a">  
</div>
<br>

## Development setup

<br>

- 테이블 논리 구성, 작업 프로젝트 생성, 공유 repository 생성
- H2DB 라이브러리 추가 및 활용(pom.xml파일과 properties 파일 수정,sql 파일을 프로젝트에 추가)
 1. 테이블 정의 : 기본키(Pk), Null 허용 여부(Null/Not Null), 자동 증가(Auto Increment)설정을 포함한 테이블 정의
 2. 테이블 생성 : customerqna 테이블 생성하고 테스트 용 데이터를 추가
 3. Test Mapper를 작성한 뒤 h2-console실시간 입출력 테스트

<br>

<p align="center">[sql파일의 작성 내용 중 일부]</p>

<div align="center">
  <img src="https://github.com/user-attachments/assets/52b38f6e-7d02-41bb-bd9f-80b373b183ac" width="600">
</div>

<br>

## 테이블 구성

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/547c4eeb-a388-4a7c-8b2e-3a4bf7293272" width="400">
</div>

<br>

## work process

<br>

- 익명 게시판을 기획하고, 사용자 요구 사항을 분석하여 기능을 정의
- 테스트 과정을 간편화 하기 위해 H2DB를 사용하여 데이터를 저장하고, 서버 시작 시 필요한 테이블들을 <br> 
  자동으로 생성하도록 초기화 스크립트를 작성
- 기본적인 게시글 작성 기능을 구현한 후, 비밀번호를 통한 수정 및 삭제 기능을 추가

<br>

---

<br>

## 자주 묻는 질문

<h3>H2DB를 사용한 이유?</h3>

- MySql 하나만 쓰는게 아니라 타 DB도 사용해보고 싶었고, 수업에서 배운 H2DB를 프로젝트에 활용해보고 싶었습니다.
- 각 DB가 가진 특성과 장단점이 다른것을 직접 적용해보면서 알 수 있었습니다.
- H2DB는 서버를 재시작했을 때, sql파일에 작성된 내용을 제외한 나머지 데이터들은 초기화되기 때문에 <br>
  빠른 테스트를 진행할 수 있습니다.

<br>

---

## Authors

* GitHub  [@이나겸](https://github.com/NakyeomLee)
