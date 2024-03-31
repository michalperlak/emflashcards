import 'bulma/css/bulma.min.css';
import React, {useContext, useEffect, useState} from 'react';
import {Container} from "react-bulma-components";
import {FlashcardArray} from "react-quizlet-flashcard";
import {getCards} from "../../application/cards";
import CardAnswer from "../../components/card-answer/card-answer";
import Card from "../../components/card/card";
import SessionSummary from "../../components/session-summary/session-summary";
import {AppContext} from "../../context/app-context";
import './review.css';

const getCardsForReview = async (user, learningSession) => {
    const data = await getCards(user);
    learningSession.cards = data;
};

const newSession = (activeSession) => {
    if (!activeSession || activeSession.finished) {
        return {
            cards: [], done: [], rates: {}, finished: null, started: new Date()
        };
    }
    return activeSession;
}

const ReviewPage = () => {
    const {user, activeSession, updateState} = useContext(AppContext);
    const [learningSession] = useState(newSession(activeSession));

    useEffect(() => {
        if (learningSession.cards && learningSession.cards.length > 0) {
            return;
        }
        getCardsForReview(user, learningSession)
            .catch(console.error)
            .then(() => updateState({activeSession: learningSession}));
    }, [user, learningSession, updateState])

    return (
        <Container>
            <div className="flashcards-wrapper">
                { learningSession.cards && !learningSession.finished &&
                    <FlashcardArray
                        cards={learningSession.cards.map(card => (
                            {
                                id: card.id,
                                frontContentStyle: {
                                    height: "100%"
                                },
                                frontHTML: <Card question={card.question}/>,
                                backHTML: <CardAnswer answer={card.answer} cardId={card.id} session={learningSession}/>
                            }
                        ))}
                    />
                }
            </div>
            {
                learningSession.finished && <SessionSummary session={learningSession} />
            }
        </Container>
    );
}

export default ReviewPage;