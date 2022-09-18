# Instrucciones para utilizar un progress bar

1. En el activity buscar el control llamado progress bar (horizontal) e insertarlo
2. Ingresarle un porcentaje por defecto, en la propiedad llamada `progress`, existen otras propiedades interesantes como `max`y `min`, las cuales determinan el minimo de progreso y el máximo que puede tener la barra. Para cambiar el color del progreso y del fondo se utilizan las propiedades `progressTint` y `progressBackgroundTint` respectivamente.
3. Existen diferentes formas de manipular el progreso de la barra, una de ellas es con el método `incrementProgressBy`, el cuál incrementa el progreso por un número, o `setProgress`que reemplaza el progreso existente por otro, los dos se pueden ver en el ejemplo