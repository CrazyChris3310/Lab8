# Lab8

## Task:

Заменить консольный клиент на клиент с графическим интерфейсом пользователя(GUI). 
В функционал клиента должно входить:

1. Окно с авторизацией/регистрацией.
2. Отображение текущего пользователя.
3. Таблица, отображающая все объекты из коллекции
    <ol type = "a">
      <li>Каждое поле объекта - отдельная колонка таблицы.</li>
      <li>Строки таблицы можно фильтровать/сортировать по значениям любой из колонок. Сортировку и фильтрацию значений столбцов реализовать с помощью Streams API.</li>
  </ol>
4. Поддержка всех команд из предыдущих лабораторных работ.
5. Область, визуализирующую объекты коллекции.
<ol type = "a">
        <li>Объекты должны быть нарисованы с помощью графических примитивов с использованием <b>Graphics, Canvas</b> или аналогичных средств графической библиотеки.</li>
        <li>При визуализации использовать данные о координатах и размерах объекта.</li>
        <li>Объекты от разных пользователей должны быть нарисованы разными цветами.</li>
        <li>При нажатии на объект должна выводиться информация об этом объекте.</li>
        <li>При добавлении/удалении/изменении объекта, он должен автоматически появиться/исчезнуть/измениться  на области как владельца, так и всех других клиентов.</li>
        <li>При отрисовке объекта должна воспроизводиться согласованная с преподавателем анимация.</li>
    </ol>
6. Возможность редактирования отдельных полей любого из объектов (принадлежащего пользователю). Переход к редактированию объекта возможен из таблицы с общим списком объектов и из области с визуализацией объекта.

7. Возможность удаления выбранного объекта (даже если команды remove ранее не было).

8. Перед непосредственной разработкой приложения необходимо согласовать прототип интерфейса с преподавателем. Прототип интерфейса должен быть создан с помощью средства для построения прототипов интерфейсов(mockplus, draw.io, etc.)


### Вариант:
1. Интерфейс должен быть реализован с помощью библиотеки Swing
2. Графический интерфейс клиентской части должен поддерживать русский, сербский, итальянский и испанский (Сальвадор) языки / локали. Должно обеспечиваться корректное отображение чисел, даты и времени в соответстии с локалью. Переключение языков должно происходить без перезапуска приложения. Локализованные ресурсы должны храниться в файле свойств.
