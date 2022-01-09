# NewsApp-MVP-Clean_Architecture-Pagging_3.0-PagingSource-RemoteMediator

Пагинация новостей выполнялась при помощи библиотеки `paging 3.0`. 

- Реализовал PagingSource, приложение работает в соответствии с заданием (Но не выполняется условие кэширования новостей).

- Для кэширования новостей в базу данных необходимо было реализовать RemoteMediator, а не PagingSource. 
Реализовал, работает нормально, кроме одного момента: пагинации нет, т.е. загрузка новостей происходит сразу до конечного элемента. 
Логами удалось выяснить, что это происходит из-за вызова LoadType.APPEND. На протяжении 3 дней сидел над этой задачей, но решить почему это происходит не удалось. 
Все выполнено в соответствии с документацией android.developer. На stackoverflow подобные проблемы у дргуих тоже возникали, но они остались без ответа:

https://stackoverflow.com/questions/68296349/why-paging3-with-mediator-load-append-infinitely
https://stackoverflow.com/questions/64557264/android-paging-3-and-remotemediator-requests-for-list-while-user-doesnt-reached

для тестирования remotemediator нужно в NewsPresenter заменить данный код:
```
val flow = Pager(
        PagingConfig(20, 5)
        //remoteMediator = NewsRemoteMediator(newsDao, ApiUtilities.getNewsApiService())
    ) {
        //newsDao.getNews()
        newsPagingSource.create()
    }.flow
```

на код:
```
val flow = Pager(
        PagingConfig(20, 5),
        remoteMediator = NewsRemoteMediator(newsDao, ApiUtilities.getNewsApiService())
    ) {
        newsDao.getNews()
        //newsPagingSource.create()
    }.flow
```

Нужна помощь в этом вопросе, и буду признателен, если дадите обратную связь по данному вопросу.

Помимо вышеуказанной проблемы не успел до конца разобраться в Dagger 2, для выполнения условия di. 
В проекте только наброски необходимых AppComponent и AppModule. В ближайшее время доделаю, но до 10 января не успел, поэтому это не в зачет.

Всё остальные требования задания выполнил, приложение работает без сбоев.
