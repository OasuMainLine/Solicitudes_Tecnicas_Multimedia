# Instrucciones

1. Crear una clase que extienda de BroadcastReceiver y sobreescribir el método `onReceive`, dentro de este método ira todo lo que se quiera hacer una vez la alarma se active
2. Declarar el receiver en AndroidManifest.xml, de la siguiente forma:
```
...
<application ... > 
    <receiver android:name=".<El nombre de nuestra clase receiver>" />
</application>
```  
3. Luego necesitamos una instancia de AlarmManager y Pending intent:
```
...
private AlarmManager alarmManager;
private PendingIntent alarmIntent;
...
    alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, AlarmReceiver.class);
    alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
```
4. Luego cuando queramos ejecutar una alarma solo basta con llamar al método `alarmManager.set(TIPO DE ALARMA, FECHA A LANZAR EN MILLISEGUNDOS, alarmIntent)`, como en el siguiente ejemplo:
```
...
    Calendar calendar = Calendar.getInstance();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
    try {
        calendar.setTime(sdf.parse(date + " " + time));
    } catch (ParseException e) {
        e.printStackTrace();
    }


    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
...
```

5. Existen varios tipos de alarma, la RTC en este caso hace uso de la zona horaria, perfecta para situaciones en las que se necesita una fecha exacta, mientras que la Elapsed_RealTime mide el tiempo desde que el sistema se inicio, perfecto para intervalos.

Para más información con respecto a los tipos de alarmas: [Developers Android - Alarms](https://developer.android.com/training/scheduling/alarms)