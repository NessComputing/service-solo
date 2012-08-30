package ness.solo;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.nesscomputing.event.NessEvent;
import com.nesscomputing.event.NessEventSender;
import com.nesscomputing.event.NessEventType;
import com.nesscomputing.logging.Log;

public class MessageTickerJob implements Job
{
    private static final AtomicLong TICKER_COUNT = new AtomicLong(0L);
    private static final NessEventType TICKER_EVENT = NessEventType.getForName("ticker");
    private static final UUID TICKER_UUID = UUID.randomUUID();

    private final Log LOG = Log.findLog();

    private final NessEventSender sender;

    @Inject
    public MessageTickerJob(final NessEventSender sender)
    {
        this.sender = sender;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        LOG.debug("Tick!");
        final NessEvent event = NessEvent.createEvent(TICKER_UUID, TICKER_EVENT, ImmutableMap.of("count", Long.toString(TICKER_COUNT.incrementAndGet())));
        sender.enqueue(event);
    }
}
