
CREATE OR REPLACE STREAM "STOCK_STREAM1" (
  "stockCode"       VARCHAR(10),
  "WINDOWTIME"      TIMESTAMP,
  "currentPrice"    DECIMAL(7, 2)
);

CREATE OR REPLACE PUMP "STOCK_PUMP1" AS
INSERT INTO "STOCK_STREAM1"
SELECT STREAM
  "SOURCE_SQL_STREAM_001"."stockCode" AS "stockCode",
  STEP("SOURCE_SQL_STREAM_001"."ROWTIME" BY INTERVAL '5' SECOND) AS "WINDOWTIME",
  SUM("SOURCE_SQL_STREAM_001"."currentPrice") AS "currentPrice"
FROM "SOURCE_SQL_STREAM_001"
GROUP BY ("SOURCE_SQL_STREAM_001"."stockCode"),
    STEP("SOURCE_SQL_STREAM_001"."ROWTIME" BY INTERVAL '5' SECOND);


CREATE OR REPLACE STREAM "STOCK_STREAM2" (
  "stockCode"       VARCHAR(10),
  "WINDOWTIME"      TIMESTAMP,
  "currentPrice"    DECIMAL(7, 2),
  "lastPrice"       DECIMAL(7, 2),
  "trend"           VARCHAR(12)
);

CREATE OR REPLACE PUMP "STOCK_PUMP2" AS
INSERT INTO "STOCK_STREAM2"
SELECT STREAM
  t."stockCode",
  t."WINDOWTIME",
  t."currentPrice",
  t."lastPrice",
  CASE 
      WHEN t."lastPrice" > t."currentPrice" THEN 'DECREASING'
      WHEN t."lastPrice" < t."currentPrice" THEN 'INCREASING'
      ELSE 'NO CHANGE'
  END AS "trend"
  FROM (
    SELECT STREAM  "stockCode", "WINDOWTIME", "currentPrice",
      LAG("currentPrice", 1, 0) OVER WIN AS "lastPrice"
    FROM "STOCK_STREAM1"
    WINDOW WIN AS (PARTITION BY "stockCode" ROWS 1  PRECEDING)
  ) t;

