package com.twinkle.server.io;

import com.twinkle.data.DataGame;
import com.girlkun.network.session.ISession;
import com.girlkun.network.example.KeyHandler;


public class MyKeyHandler extends KeyHandler {

    @Override
    public void sendKey(ISession session) {
        super.sendKey(session);
        DataGame.sendVersionRes((MySession) session);
    }

}






















