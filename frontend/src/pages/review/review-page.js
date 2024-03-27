import React from 'react';
import 'bulma/css/bulma.min.css';
import './review.css';
import {Container} from "react-bulma-components";
import {FlashcardArray} from "react-quizlet-flashcard";
import CardAnswer from "../../components/card-answer/card-answer";
import Card from "../../components/card/card";
import {data} from "./mock";

const ReviewPage = () => {
    return (
        <Container>
            <div align="center" style={{"margin-top":  "20%"}}>
                    <FlashcardArray cards={data.map(card => (
                        {
                            id: card.id,
                            frontHTML: <Card question={card.question}/>,
                            backHTML: <CardAnswer answer={card.answer}/>
                        }
                    ))}/>
            </div>

        </Container>
    );
}

export default ReviewPage;