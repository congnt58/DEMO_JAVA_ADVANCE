function saveValue(key, value) {
  localStorage.setItem(key, value);
}

function getValue(key) {
  return localStorage.getItem(key);
}

function clearStorage() {
  localStorage.clear();
}

function removeValue(key) {
  localStorage.removeItem(key);
}
