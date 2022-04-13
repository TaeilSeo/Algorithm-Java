# Algorithme
## BOJ

##### 01520. 내리막길
##### 01261. 알고스팟
##### 01916. 최소비용 구하기
##### 02133. 타일 채우기
##### 02477. 참외밭
##### 02573. 빙산
##### 02606. 바이러스
##### 02839. 설탕 배달
##### 07569. 토마토
##### 10026. 적록색약
##### 11729. 하노이탑 이동순서
##### 12865. 평범한 배낭
##### 15649. N과 M(1)
##### 15961. 회전초밥(고)
##### 16637. 괄호 추가하기

## [성공]
### 02477. 참외밭
> - 처음엔 무작정 변의 길이가 가장 큰 input index를 기준으로 작은 직사각형의 가로, 세로를 구하려 했었다.
> - 그러다 각각의 변이 서로 마주본다는 것을 파악하고, 규칙을 찾다가 모듈러스 연산이란 것을 깨달았다.
> - 사용한 방식이 문제에 접근하는 정확한 해답인지는 모르겠으나, 내가 의도했던 바를 달성했던 문제였던 건 확실하다.
### 02839. 설탕배달
> - 5kg 기준으로 더 많이 담으면 되기 때문에, 그리디하게 접근했다.
> - 3 * n 을 빼줬을 때 5로 나머지 없이 나누어지면 종료, 남은 설탕의 무게가 0 < weight < 3을 만족한다면 -1을 리턴해 줬다.
### 07569. 토마토
> - 가장 먼저 고민됐던 것은, n일에 익은 토마토가 또 다시 n일에 영향을 주면 안되는데 이를 어떻게 해결할까에 대한 것 이었다.
> - 그런데, 초기 익은 토마토들만 Queue에 넣어준 뒤 BFS 식으로 돌려주면 위의 고민을 해결할 수 있지 않을까 라고 생각했다.
> - 대부분 2차원배열만을 사용하던데, 나는 단순히 생각하기 쉽게 3차원 배열을 사용했다.
### 10026. 적록색약
> - 적록색약이 있는 사람, 없는 사람의 2경우를 확인해야 해서 bfs를 두 종류로 만들었다.
> - 다만 중복되는 코드가 너무 길어져, 한 bfs 내에 조건문 설정하여 경우를 나눠 주는 것이 더 효율적인 것 같다.
### 12865. 평범한 배낭
> - 0 1 knapsak 으로 배열 2개를 사용하여 해결하였다.
> - 다음에는 꼭 점화식..
### 15649. N과 M(1)
> - 순열을 구하는 문제
> - StringBuilder를 사용하여 출력

## [실패]
### 01520. 내리막길[해결]
> - 로직이 틀리진 않았으나, 제한 시간을 만족하지 못함
> - 가지치기를 어떻게 좀 더 해볼 수 있을까..
> - + DP를 추가 적용해야 제한시간내에 해결 가능 했다.
> - + 이미 지나간 경로에 대해 경우의 수를 기록하여, 중복해서 지나갈 필요가 없도록 하여 시복잡도를 줄임
### 01916. 최소비용 구하기[해결]
> - 다익스트라 사용
> - 알고리즘 구현 기억이 흐릿해서 결국 실패.
> - 복습할 수 있었던 좋은 문제
### 02133. 타일채우기[해결]
> - 어떻게 점화식을 구했는데, 답에 근접하긴 하지만 오차가 발생하여 다른 코드들을 참고하였다.
> - DP라고 생각이 들면 앞뒤번째의 연관성, 즉 규칙을 찾는 것이 핵심인 것 같다.
### 02573. 빙산[해결]
> - dfs든 bfs든 2번의 과정이 필요하다.
> - 얼음 녹이는 과정에서 1번, 빙산이 한 덩어리인지 확인하는 과정에서 1번
> - 최초 queue에 빙산들을 넣어줄 때, 사방이 빙산으로 둘러쌓인건 넣어주면 안되는데 넣어주었고,
> - 빙산이 한 덩어리인지 체큰하는 함수 로직에 문제점이 있는 것 같아 보인다.
> + 2차원 배열의 deep copy가 제대로 수행되지 못했다.
> + 제일 처음부터 두 덩어리 이상인 경우를 확인하게 했다.
> + 한 덩어리가 끝까지 두 덩어리로 분리되지 않는 경우, 마지막 년도에 다 녹게 된다는 것이므로, 년수를 0으로 초기화 해주고 프로그램을 종료하게 했다.
### 11729. 하노이탑 이동순서
> - 오랜맨에 보니 감도 안왔던 문제..
> - 좋은 문제는 아니나, 내용 복습은 후에 꼭 해두기
### 15961. 회전초밥(고)[해결]
> - br 사용안하면 시간초과로 터지기 쉬운 문제인데, 표준 입출력으로 해결한 동기분의 코드가 있어 참고했다.
> - 문제를 제대로 이해하지 못했을 때 초밥 가짓수가 왜 주어지지 라고 생각했었는데, 제대로 파악하고 나니 문제 해결의 핵심이었다.
> - 문제를 제대로 이해하려고 더욱 더 노력해야겠다.
### 16637. 괄호 추가하기[해결]
> + 연산인걸 보고 후위기식 관련인 줄 알았으나, 전-혀 아니었다.
> + 정말 한순간 혹시 DFS인가 생각했었지만, 가볍게 넘어갔었다.
> + 문제에서 요구하는 요소가 들어간다/안간다 로 해결될 수 있다면, DFS를 항상 떠올릴 수 있어야 하는 것 같다.
* * *         
## SWEA
##### 01249. [S/W 문제해결 응용] 4일차 - 보급로
##### 01949. [모의 SW 역량테스트] 등산로 조성
##### 02117. [모의 SW 역량테스트] 홈 방범 서비스
##### 03124. 최소 스페닝 트리
##### 03752. 가능한 시험 점수
##### 04013. [모의 SW 역량테스트] 특이한 자석
##### 05656. [모의 SW 역량테스트] 벽돌 깨기

## [성공]
### 01249. [S/W 문제해결 응용] 4일차 - 보급로
> - bfs + dp 로 해결했다.
> - 뭔가 될 것 같은데 왜이렇게 무한루프에 빠지나.. 뭔가를 잘못하고 있다고 생각하다가
> - 메모이제이션 값을 업데이터 안해주고 있던 것을 발견하고 수정했더니 정상적으로 수행됐다.
> - dfs, bfs 문제를 풀때는 조급함을 버려야 되는 것 같다. 너무 급하게 가려고 해서 자꾸 넘어지는 듯한 느낌이 든다.
> - + 기존의 Queue만 PriorityQueue 로 바꾸고, Comparator 설정만 해줬는데도 불구하고 수행시간이 515ms → 175ms 로 확 줄었다.
> - + bfs에 dp를 적용할 때는 PriorityQueue 를 활용해 보는 것도 좋을 것 같다.
##### 01949. [모의 SW 역량테스트] 등산로 조성
> - 방문체크의 중요성을 상기할 수 있었던 문제였다.
> - dfs, bfs 풀이에서 시작지점이 2개 이상일 경우, 시작지점 또한 방문 체크를 원상태로 복구시켜줘야 된다는 것을 기억해야 겠다.
### 03124. 최소 스페닝 트리
> - 몇 번 반복하면서 이해했던 개념이지만, 오랜만에 하니 중간중간 막혔었다
> - 숙달되도록 주기적인 복습을 수행해야겠다.
### 04013. [모의 SW 역량테스트] 특이한 자석
> - 주어진 자석을 회전하는 시뮬레이션 문제였다.
> - 자석이 회전하기 위한 조건을 검사하는 코드 구현에서 다소 시간이 걸렸다.
> - 자석이 몇개가 들어오든 상관없게 만들고 싶었으나, 시간관계상 문제에만 적용될 수 있도록 구현하였다.
### 05656. [모의 SW 역량테스트] 벽돌 깨기
> - 벽돌을 깨는 함수, 조정하는 함수, 중복순열을 돌리는 함수 등을 선언하여 풀었다
> - 수행 중, 존재하는 모든 벽돌이 깨졌을때 종료하는 조건을 추가하자 수행시간이 1900ms -> 500ms 대로 줄었다
> - 250ms 대 까지는 줄일 수 있을 것으로 보인다.

## [실패]
### 02117. [모의 SW 역량테스트] 홈 방범 서비스
> - .
### 03752. 가능한 시험 점수[해결]
> - 2022-03-08
> - 무작정 부분집합을 사용하려고 했었으나, 제한시간 2초를 극복하지 못하였고 결국 자력으로 풀지는 못했다.
> - 해결에 성공한 스터디원들의 코드 흐름이 일치하여, 그 중 HashSet 자료구조를 사용한 코드를 바탕으로 이해해 보기로 했다.
> - 전체 N 크기 만큼 반복하면서 해당 index의 점수를 현재 HashSet의 모든 원소들에 더 해준다.
> - 중복은 자동으로 걸러지며, 유일한 값들만 남게 된다.
> - DP의 중요성이 점점 대두되는 것 같다.
* * *
## Programmers
##### dfs_bfs_4
## [성공]
### dfs_bfs_4
> - 일반적인 dfs 문제이나, 기준이 사전 순이기 때문에 별도의 정렬이 필요하다
> - 해당 문제의 경우, 주어진 티켓들을 정렬해준 뒤 dfs를 사용한다면 주어진 조건을 만족할 수 있었다.

## [실패]
