-- Procedure: CHECK_CUSTOMER_ID_EXISTS
-- Purpose: Accepts two input parameters (customer_id, customeridtype) and returns an OUT BOOLEAN
--          indicating whether a row exists in CUSTOMER with matching CUSTOMERID and CUSTOMERIDTYPE.
--
-- Notes:
-- - This uses PL/SQL BOOLEAN for the OUT parameter, which is supported within PL/SQL.
--   If you intend to call this from Java/JDBC or SQL clients directly, consider creating
--   a wrapper that returns NUMBER(1) or CHAR(1), as JDBC cannot bind PL/SQL BOOLEAN
--   parameters in older drivers/databases.
--
-- Example (PL/SQL anonymous block test):
--   DECLARE
--     v_exists BOOLEAN;
--   BEGIN
--     CHECK_CUSTOMER_ID_EXISTS(p_customer_id => '1234567890',
--                              p_customeridtype => 'NAT_ID',
--                              p_exists => v_exists);
--     IF v_exists THEN
--       DBMS_OUTPUT.PUT_LINE('Exists');
--     ELSE
--       DBMS_OUTPUT.PUT_LINE('Does not exist');
--     END IF;
--   END;
--   /
--
-- To create or replace the procedure, run this script in an Oracle SQL*Plus/SQLcl/IDE session.

CREATE OR REPLACE PROCEDURE CHECK_CUSTOMER_ID_EXISTS(
  p_customer_id     IN  CUSTOMER.CUSTOMERID%TYPE,
  p_customeridtype  IN  CUSTOMER.CUSTOMERIDTYPE%TYPE,
  p_exists          OUT BOOLEAN
) AS
  v_dummy NUMBER;
BEGIN
  -- Attempt to find at least one matching row
  SELECT 1 INTO v_dummy
    FROM CUSTOMER
   WHERE CUSTOMERID = p_customer_id
     AND CUSTOMERIDTYPE = p_customeridtype
     FETCH FIRST 1 ROWS ONLY;

  p_exists := TRUE;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    p_exists := FALSE;
  WHEN OTHERS THEN
    -- Re-raise unexpected errors for visibility
    RAISE;
END CHECK_CUSTOMER_ID_EXISTS;
/

-- Optional: Wrapper function returning NUMBER for JDBC callers
-- Uncomment to create, if needed.
-- CREATE OR REPLACE FUNCTION CHECK_CUSTOMER_ID_EXISTS_NUM(
--   p_customer_id    IN  CUSTOMER.CUSTOMERID%TYPE,
--   p_customeridtype IN  CUSTOMER.CUSTOMERIDTYPE%TYPE
-- ) RETURN NUMBER AS
--   v_exists BOOLEAN;
-- BEGIN
--   CHECK_CUSTOMER_ID_EXISTS(p_customer_id, p_customeridtype, v_exists);
--   RETURN CASE WHEN v_exists THEN 1 ELSE 0 END;
-- END;
-- /
