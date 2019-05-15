-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE todoItems (
  key       VARCHAR(64) PRIMARY KEY,
  owner     VARCHAR(64) NOT NULL,
  task      VARCHAR(512) NOT NULL
)
