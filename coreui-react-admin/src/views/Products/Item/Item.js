import React from 'react';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button ,Container, Row, Col} from 'reactstrap';
import './Item.scss'

const Item = ({items}) => {
    
    const getElementOfItem = (item, index) => {
        return (
            <Col xs="6" sm="4" key={index}>
                <Card key={index}>
                    <CardImg top width="100%" src="https://i.ytimg.com/vi/7SSu0KtXI2c/maxresdefault.jpg" />
                    <CardBody>
                        <CardTitle>Card title</CardTitle>
                        <CardText>the card title and make up the bulk of the card's content.</CardText>
                        <Button>Button</Button>
                    </CardBody>
                </Card>
            </Col>
        )
    }

    return (
        <Container>
            { items.map( (item, index) => { return getElementOfItem(item, index); }) }
        </Container>
    );
}

export default Item;