INSERT INTO roles(role)
VALUES ('USER');
INSERT INTO roles(role)
VALUES ('EDITOR');
INSERT INTO roles(role)
VALUES ('ADMIN');

INSERT INTO users(user_name,password,enabled)
VALUES ('elvis','1234', true);
INSERT INTO users(user_name,password,enabled)
VALUES ('donald','1234', true);
INSERT INTO users(user_name,password,enabled)
VALUES ('vlad','1234', true);

INSERT INTO user_roles (user_id , role_id)
VALUES ('1', '1');
INSERT INTO user_roles (user_id , role_id)
VALUES ('1', '2');
INSERT INTO user_roles (user_id , role_id)
VALUES ('1', '3');
INSERT INTO user_roles (user_id , role_id)
VALUES ('2', '1');
INSERT INTO user_roles (user_id , role_id)
VALUES ('2', '2');
INSERT INTO user_roles (user_id , role_id)
VALUES ('3', '1');

INSERT INTO persons (user_id, first_name, last_name, screen_name)
	VALUES (1,'Elvis','Presli','Сhipmunk');
INSERT INTO persons (user_id, first_name, last_name, screen_name)
	VALUES (2,'Donald','Trump','Duck');
INSERT INTO persons (user_id, first_name, last_name, screen_name)
	VALUES (3,'Vladimir','Putin','SuperMan');

INSERT INTO tags(tag)
VALUES ('politics');
INSERT INTO tags(tag)
VALUES ('society');
INSERT INTO tags(tag)
VALUES ('sport');
INSERT INTO tags(tag)
VALUES ('world');
INSERT INTO tags(tag)
VALUES ('science');
INSERT INTO tags(tag)
VALUES ('nature');
INSERT INTO tags(tag)
VALUES ('accidents');

INSERT INTO news(author, title, article, post, publication_date)
VALUES (1,'Гроссмейстер Борис Гельфанд даст в Минске сеанс одновременной игры юным шахматистам',
					'В Минск из Израиля прилетел международный гроссмейстер, воспитанник белорусской школы шахмат, победитель Кубка мира — 2009, претендент на звание чемпиона мира 2012 года Борис Гельфанд, сообщает пресс-служба Министерства спорта и туризма.',
					'<p>В белорусскую столицу именитого спортсмена пригласила Белорусская федерация шахмат.</p><p>Борис Гельфанд уже принял участие в мероприятиях, связанных с подачей заявки на проведение в Минске Всемирной шахматной олимпиады 2022 года.</p><p>С 9 по 13 апреля Борис Гельфанд проведет тренировочную сессию с национальной сборной страны по шахматам в Школе шахмат ФИДЕ в Беларуси.</p><p>А 14 апреля он проведет сеанс одновременной игры с юными (2006 года рождения и моложе) призерами республиканских первенств страны по шахматам.</p><p>Сеанс пройдет в Национальном художественном музее и начнется в 11.30.</p><p>Все юные спортсмены получат в подарок книгу Бориса Гельфанда «Принятие позиционных решений в шахматах» или «Принятие динамических решений в шахматах» с автографом автора.</p><p>15 апреля, в 11.00, в Школе шахмат ФИДЕ состоится творческая встреча гроссмейстера с юными шахматистами и их родителями.</p>',
					'2018/04/09'
);
INSERT INTO news(author, title, article, post, publication_date)
VALUES (2,'СМИ: Великобритания потребовала больше доказательств химатаки в Сирии',
					'Великобритании нужно больше доказательств, чтобы принять участие в военном ответе на химическую атаку в сирийском городе Дума. Об этом, как пишет Times, Мэй заявила в разговоре с Дональдом Трампом и Эмманюэлем Макроном.',
					'<p>Для того чтобы Великобритания присоединилась к военному ответу на применение химического оружия правительственными войсками в Сирии, требуется больше доказательств. Об этом, как сообщает The Times, британский премьер Тереза Мэй заявила в разговоре с президентом США Дональдом Трампом.</p><p>Премьер не согласилась провести быструю ответную операцию, поскольку место предполагаемой химической атаки в Восточной Гуте готовы были посетить эксперты Организации по запрещению химического оружия (ОЗХО).</p><p>Ранее 9 апреля Трамп поставил очень жесткие временные рамки: он пообещал в течение 48 часов определить, как он ответит на очередную химатаку в Сирии. При этом он выразил уверенность, что за инцидент несут ответственность власти Сирии во главе с президентом Башаром Асадом, а также союзные с ним Россия и Иран.</p><p>По информации издания, в ходе внутренних обсуждений министр иностранных дел Борис Джонсон настаивал, что Великобритания не должна оставаться в стороне, а применение химического оружия не должно оставаться безнаказанным. Однако сама Мэй, пишет Times, скептически отозвалась об эффективности прошлогоднего удара по авиабазе в Хомсе, который Трамп предпринял в ответ на очередной случай применения химического оружия.</p><p>Газета также отмечает, что Макрон, видимо, тоже ограничил участие Франции некоторыми условиями. В частности, он заявил, что удар следует наносить только по химическим объектам режима Асада.</p><p>Телефонные переговоры Мэй с Трампом и Макроном состоялись 10 апреля. В офисе премьера сообщили, ответ международного сообщества лидеры посчитали необходимым для того, чтобы предотвратить использование химического оружия в мире.</p><p>Официальная позиция Москвы заключается в том, что факт химической атаки в городе Дума не подтвержден и не доказан, а видеосъемка с места событий является постановочной.</p>',
					'2018/04/10'
);
INSERT INTO news(author, title, article, post, publication_date)
VALUES (2,'СМИ: при падении самолета на севере Алжира погибли 200 человек',
					'По меньшей мере 200 человек погибли в результате падения военного самолета на севере страны, сообщила в среду газета «Аш-Шарк аль-Аусат».',
					'<p>Инцидент произошел в провинции Блида, близ аэропорта города Буфарик.</p><p>Подробности произошедшего уточняются.</p>',
					'2018/04/11'
);
INSERT INTO news(author, title, article, post, publication_date)
VALUES (2,'Бобер прятался в детском саду Бобруйска. Животное отловили и выпустили на волю.',
					'На территории детского сада в Бобруйске 10 апреля обнаружили бобра. Зверек прятался в системе вентиляции на территории учреждения.',
					'<p>Сотрудников МЧС в 7 утра вызвала воспитатель детского сада. Спасатели оцепили здание и отловили животное. После осмотра в ветлечебнице бобра отпустили в естественную среду обитания.</p>',
					'2018/04/11'
);

INSERT INTO news_tags (news_id , tag_id)
VALUES ('1', '3');
INSERT INTO news_tags (news_id , tag_id)
VALUES ('1', '4');
INSERT INTO news_tags (news_id , tag_id)
VALUES ('2', '1');
INSERT INTO news_tags (news_id , tag_id)
VALUES ('2', '4');
INSERT INTO news_tags (news_id , tag_id)
VALUES ('3', '7');
INSERT INTO news_tags (news_id , tag_id)
VALUES ('3', '4');
INSERT INTO news_tags (news_id , tag_id)
VALUES ('4', '6');
INSERT INTO news_tags (news_id , tag_id)
VALUES ('4', '2');

INSERT INTO comments (person_id , news_id, comment)
VALUES ('3', '1','Гельфонд чемпион!!!');
INSERT INTO comments (person_id , news_id, comment)
VALUES ('1', '1','Даже не близко.');
INSERT INTO comments (person_id , news_id, comment)
VALUES ('2', '1','А какой у него рейтинг сейчас?');
INSERT INTO comments (person_id , news_id, comment)
VALUES ('1', '1','2695 в классике.');
INSERT INTO comments (person_id , news_id, comment)
VALUES ('2', '4','Бобрик :)');

INSERT INTO likes (news_id , person_id)
VALUES (1,1);
INSERT INTO likes (news_id , person_id)
VALUES (2,1);
INSERT INTO likes (news_id , person_id)
VALUES (3,1);
INSERT INTO likes (news_id , person_id)
VALUES (4,1);
INSERT INTO likes (news_id , person_id)
VALUES (1,2);
INSERT INTO likes (news_id , person_id)
VALUES (2,2);
INSERT INTO likes (news_id , person_id)
VALUES (1,3);
INSERT INTO likes (news_id , person_id)
VALUES (2,3);
INSERT INTO likes (news_id , person_id)
VALUES (4,3);
