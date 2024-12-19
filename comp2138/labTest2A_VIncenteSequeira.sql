/*
Vincente Sequeira
Std ID: 101484793
1.
*/
DECLARE
  item_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO item_count
  FROM om.items
  WHERE price >= 15;

  DBMS_OUTPUT.PUT_LINE(item_count || ' items are greater than or equal to $15.');
END;

/*
2.
*/
CREATE OR REPLACE PROCEDURE insert_items (
  p_item_id   IN om.items.item_id%TYPE,
  p_title     IN om.items.title%TYPE,
  p_price     IN om.items.price%TYPE,
  p_category  IN om.items.category%TYPE
) IS
BEGIN
  INSERT INTO om.items (item_id, title, price, category)
  VALUES (p_item_id, p_title, p_price, p_category);

  DBMS_OUTPUT.PUT_LINE('Item inserted successfully.');
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    DBMS_OUTPUT.PUT_LINE('A DUP_VAL_ON_INDEX error occurred.');
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('An unknown exception occurred.');
END;
/

