<!DOCTYPE html>
<html>

<body>
<h1>RESTful-WS client prototype</h1>
Become a study participant


<form action="../rest/admin/register" method="POST">
  <label for="user"></label>
  <input name="user" />
  <br/>

  <br/>

  <input type="submit" value="Submit" />



  </form>
      <c:if test="${param.guess=='Java'}">You guessed it!</c:if>
      <c:if test="${param.guess!='Java'}">You are wrong</c:if>
      
       1 + 2 + 3 = <c:out value="${1 + 2 + 3}" />

    <form action="index.jsp" method="POST">Guess what computer language
                        I am thinking of?
    <input type="text" name="guess" />

    <input type="submit" value="Try!" />
    </form>

</body>
</html>