function search() {
  const query = document.getElementById('searchh').value;
  window.location.href = `SearchedSpecies.jsp?title=${query}`;
  return false;
}